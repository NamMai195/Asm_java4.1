package com.poly.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;
import com.poly.service.impl.HistoryServiceImpl;
import com.poly.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class VideoController
 */
@WebServlet(name = "VideoControllerOfAdmin", urlPatterns = { "/admin/video" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HistoryService historyservice = new HistoryServiceImpl();

	public VideoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private VideoService videoservice = new VideoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String href = request.getParameter("id");
		switch (action) {
		case "view": {
			doGetOverView(request, response);
			break;
		}
		case "list": {
			doGetList(request, response);
			break;
		}
		case "watch": {
			dogetWatch(session, href, request, response);
			break;
		}
		case "delete": {
			doGetDelete(request, response);
			break;
		}
		case "add": {
			doGetAdd(request, response);
			break;
		}
		case "edit": {
			doGetEdit(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// asm-java4/admin/video?action=view
	protected void doGetOverView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Videos> videos = videoservice.findAll();
		request.setAttribute("videos", videos);
		RequestDispatcher reqd = request.getRequestDispatcher("/views/admin/video-overview.jsp");
		reqd.forward(request, response);
	}

	protected void doGetList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Videos> videos = videoservice.findAll();
		request.setAttribute("videos", videos);
		RequestDispatcher reqd = request.getRequestDispatcher("/views/admin/Listvideo.jsp");
		reqd.forward(request, response);
	}

	// asm-java4/admin/video?action=edit&href={href}
	protected void doGetEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		try {
			// Lấy dữ liệu từ request
			String videoId = request.getParameter("videoId");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String href = request.getParameter("href");
			boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

			// Tìm video trong database
			Videos video = videoservice.findById(videoId);
			if (video == null) {
				response.setStatus(404); // Not Found
				return;
			}

			// Cập nhật thông tin video
			video.setTitle(title);
			video.setDescription(description);
			video.setHref(href);
			video.setIsActive(isActive);

			// Lưu video đã cập nhật vào database
			Videos videoUpdated = videoservice.update(video);

			if (videoUpdated != null) {
				response.setStatus(204); // OK
			} else {
				response.setStatus(400); // Bad Request
			}
		} catch (Exception e) {
			response.setStatus(500); // Internal Server Error
			e.printStackTrace();
		}
	}

	// asm-java4/admin/video?action=add
	protected void doGetAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		try {
			// Lấy dữ liệu từ request
			String videoId = request.getParameter("videoId");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String href = request.getParameter("href");
			boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

			// Tạo đối tượng Video
			Videos video = new Videos();
			video.setVideoId(videoId);
			video.setTitle(title);
			video.setDescription(description);
			video.setHref(href);
			video.setIsActive(isActive);

			// Thêm video vào database
			Videos videoAdded = videoservice.create(video);

			if (videoAdded != null) {
				response.setStatus(204); // Created
			} else {
				response.setStatus(400); // Bad Request
			}
		} catch (Exception e) {
			response.setStatus(500); // Internal Server Error
			e.printStackTrace();
		}
	}

	// asm-java4/admin/video?action=delete&href={href}
	protected void doGetDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String href = request.getParameter("href");
		Videos videoDeleted = videoservice.delete(href);
		if (videoDeleted != null) {
			response.setStatus(204);
		} else {
			response.setStatus(400);
		}
	}

	protected void dogetWatch(HttpSession session, String href, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Videos video = videoservice.findByHref(href);

		List<Videos> videos = videoservice.findAllVideosExcept(href);
		request.setAttribute("videos", videos);
		request.setAttribute("video", video);
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null) {
			History history = historyservice.create(currentUser, video);
			request.setAttribute("flagLikedBtn", history.getIsliked());
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/adminvideodetail.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
