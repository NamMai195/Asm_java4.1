package com.poly.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.constant.SessionAttr;
import com.poly.dao.ShareDao;
import com.poly.entity.History;
import com.poly.entity.Share;
import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.EmailService;
import com.poly.service.HistoryService;
import com.poly.service.ShareService;
import com.poly.service.VideoService;
import com.poly.service.impl.EmailServiceImpl;
import com.poly.service.impl.HistoryServiceImpl;
import com.poly.service.impl.ShareServiceImpl;
import com.poly.service.impl.VideoServiceImpl;
import com.poly.util.TokenGenerator;

/**
 * Servlet implementation class VideoController
 */
@WebServlet("/video")
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoService videoservice = new VideoServiceImpl();
	private HistoryService historyservice = new HistoryServiceImpl();
	private EmailService emailService = new EmailServiceImpl();
	private ShareService shareService = new ShareServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String href = request.getParameter("id");
		HttpSession session = request.getSession();

		switch (action) {
		case "watch": {
			dogetWatch(session, href, request, response);
			break;
		}
		case "like": {
			dogetLike(session, href, request, response);
			break;
		}
		default:

		}
	}

	protected void dogetWatch(HttpSession session, String href, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Videos video = videoservice.findByHref(href);
		video.setViews(video.getViews() + 1);
		videoservice.update(video);
		List<Videos> videos = videoservice.findAllVideosExcept(href);

		// Giới hạn danh sách videos chỉ còn 5 phần tử
		if (videos.size() > 5) {
			videos = videos.subList(0, 5);
		}

		request.setAttribute("videos", videos);
		request.setAttribute("video", video);

		// Chuyển đổi danh sách videos sang JSON
//		ObjectMapper objectMapper = new ObjectMapper();
//		String videosJson = objectMapper.writeValueAsString(videos);
//		request.setAttribute("videosJson", videosJson);

		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null) {
			int likes = historyservice.findByVideoIdAndIsliked(video.getVideoId());
			History history = historyservice.create(currentUser, video);
			request.setAttribute("flagLikedBtn", history.getIsliked());
			request.setAttribute("like", likes);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/user/detailvideo.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void dogetLike(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		try {
			User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			if (currentUser == null) {
				response.setStatus(401); // Unauthorized (chưa đăng nhập)
				return;
			}

			// Cập nhật like/unlike
			boolean result = historyservice.updateLikeOrUnLike(currentUser, href);
			Videos video = videoservice.findByHref(href);
			// Lấy số lượng like mới
			int likes = historyservice.findByVideoIdAndIsliked(video.getVideoId());

			// Trả về số lượng like mới dưới dạng JSON
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(likes);

			response.getWriter().write(json);
			response.setStatus(200); // OK
		} catch (Exception e) {
			response.setStatus(500); // Internal Server Error
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "share": {
			doPostShare(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	protected void doPostShare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		try {
			String videoHref = request.getParameter("href");
			String email = request.getParameter("email");

			// Kiểm tra videoHref và email (có thể sử dụng regex để kiểm tra email)
			if (videoHref == null || videoHref.isEmpty() || email == null || email.isEmpty()) {
				response.setStatus(400); // Bad Request
				return;
			}

			// Tìm video trong database
			Videos video = videoservice.findByHref(videoHref);
			if (video == null) {
				response.setStatus(404); // Not Found
				return;
			}
			User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			// Gửi email chia sẻ
			emailService.sendShareVideoEmail(getServletContext(), email, video);
			Share share = new Share();
			share.setUser(currentUser);
			share.setVideo(video);
			share.setShareId(TokenGenerator.generateToken(10));
			share.setRecipientEmail(email);
			share.setShareDate(new Timestamp(System.currentTimeMillis()));
			shareService.create(share);
			response.setStatus(204); // OK
		} catch (Exception e) {
			response.setStatus(500); // Internal Server Error
			e.printStackTrace();
		}
	}
}