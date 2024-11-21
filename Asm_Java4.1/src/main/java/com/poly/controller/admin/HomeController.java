package com.poly.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dto.VideoLikedInfo;
import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.StatsService;
import com.poly.service.UserService;
import com.poly.service.impl.StatsServiceImpl;
import com.poly.service.impl.UserserviceImpl;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "HomeControllerOfAdmin", urlPatterns = { "/Homeadmin" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatsService statsService = new StatsServiceImpl();
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

		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	protected void doGetHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<VideoLikedInfo> videos = statsService.findvideoLikedInfo();
		request.setAttribute("videos", videos);
		List<User> users=userService.findAll();
		request.setAttribute("users", users);
		RequestDispatcher req = request.getRequestDispatcher("/views/admin/HomeAdmin.jsp");
		req.forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
