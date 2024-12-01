package com.poly.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dto.VideoLikedInfo;
import com.poly.entity.Share;
import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.ShareService;
import com.poly.service.StatsService;
import com.poly.service.UserService;
import com.poly.service.impl.ShareServiceImpl;
import com.poly.service.impl.StatsServiceImpl;
import com.poly.service.impl.UserserviceImpl;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "HomeControllerOfAdmin", urlPatterns = { "/Homeadmin", "/Homeadmin/repostvideo",
		"/Homeadmin/repostuser", "/Homeadmin/favorites","/Homeadmin/repostshare" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatsService statsService = new StatsServiceImpl();
	private ShareService shareService = new ShareServiceImpl();
	private UserService userService = new UserserviceImpl();

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/Homeadmin": {
			doGetHome(request, response);
			break;
		}
		case "/Homeadmin/repostvideo": {

			doGetVideoRepost(request, response);
			break;
		}
		case "/Homeadmin/repostshare": {

			doGetShareRepost(request, response);
			break;
		}
		case "/Homeadmin/repostuser": {
			System.out.println("dscsd");
			doGetUserRepost(request, response);
			break;
		}
		case "/Homeadmin/favorites": {
			System.out.println("dscsd");
			doGetFavorites(request, response);
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	protected void doGetVideoRepost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<VideoLikedInfo> videos = statsService.findvideoLikedInfo();
		request.setAttribute("videos", videos);
		RequestDispatcher req = request.getRequestDispatcher("/views/admin/videorepost.jsp");
		req.forward(request, response);

	}
	protected void doGetShareRepost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Share> videos = shareService.findAll();
		request.setAttribute("videos", videos);
		RequestDispatcher req = request.getRequestDispatcher("/views/admin/sharerepost.jsp");
		req.forward(request, response);

	}

	protected void doGetUserRepost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userService.findAll();
		request.setAttribute("users", users);
		RequestDispatcher req = request.getRequestDispatcher("/views/admin/userrepost.jsp");
		req.forward(request, response);

	}

	protected void doGetHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Thống kê tổng số video và user
		int totalVideos = statsService.getTotalVideos();
		int totalUsers = statsService.getToTalUsers();
		int totalViews = statsService.getToTalViews();
		int totalShares = statsService.getToTalShare();
		System.out.println(totalShares);
		// Đẩy dữ liệu lên JSP
		request.setAttribute("totalVideos", totalVideos);
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalViews", totalViews);
		request.setAttribute("totalShares", totalShares);
		List<VideoLikedInfo> videos = statsService.findvideoLikedInfo();
		request.setAttribute("videos", videos);
		List<User> users = userService.findAll();
		request.setAttribute("users", users);
		RequestDispatcher req = request.getRequestDispatcher("/views/admin/HomeAdmin.jsp");
		req.forward(request, response);

	}

	private void doGetFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			String videoHref = req.getParameter("href");

			List<User> users = userService.findUserLikedByHref(videoHref);
			if (users.isEmpty()) {
				resp.setStatus(400);
			} else {
				ObjectMapper mapper = new ObjectMapper();
				String dataResponse = mapper.writeValueAsString(users);
				System.out.println(users.size());
				resp.setStatus(200);
				out.print(dataResponse);
				out.flush();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
