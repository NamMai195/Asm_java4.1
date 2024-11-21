package com.poly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.poly.constant.SessionAttr;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;
import com.poly.service.impl.HistoryServiceImpl;
import com.poly.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class HomeController
 */
@WebServlet({ "/index", "/favorite", "/history" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyservice = new HistoryServiceImpl(); 

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session=request.getSession();
		switch (path) {
		case "/index": {
			dogetindex(request, response);
			break;
		}
		case "/favorite": {
			dogetfavorite(session,request, response);
			break;
		}
		case "/history": {
			dogethistory(session,request, response);
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}

	}

	protected void dogetindex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Videos> videos = videoService.findAll();
		request.setAttribute("videos", videos);
		RequestDispatcher req = request.getRequestDispatcher("views/user/index.jsp");
		req.forward(request, response);
	}

	protected void dogetfavorite(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (user == null) {
		    response.sendRedirect("login.jsp"); // Hoặc trang lỗi nếu không tìm thấy người dùng
		    return;
		}

		List<History> history=historyservice.findByUserAndIsliked(user.getId());
		List<Videos> videos=new ArrayList<Videos>();
		history.forEach(item -> videos.add(item.getVideo()));
		request.setAttribute("videos", videos);
		RequestDispatcher req = request.getRequestDispatcher("views/user/favorite.jsp");
		req.forward(request, response);
	}

	protected void dogethistory(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (user == null) {
		    response.sendRedirect("login.jsp"); // Hoặc trang lỗi nếu không tìm thấy người dùng
		    return;
		}

		List<History> history=historyservice.findByUser(user.getId());
		List<Videos> videos=new ArrayList<Videos>();
		history.forEach(item -> videos.add(item.getVideo()));
		request.setAttribute("videos", videos);
		RequestDispatcher req = request.getRequestDispatcher("views/user/history.jsp");
		req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
