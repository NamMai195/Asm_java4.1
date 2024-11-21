package com.poly.controller;

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
@WebServlet("/video")
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private VideoService videoservice=new VideoServiceImpl();
     private HistoryService historyservice =new HistoryServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String href=request.getParameter("id");
		HttpSession session=request.getSession();
		
		switch (action) {
		case "watch": {
			dogetWatch(session,href,request,response);
			break;
		}
		case "like": {
			dogetLike(session,href,request,response);
			break;
		}
		default:
			
		}
	}
	protected void dogetWatch(HttpSession session,String href,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Videos video =videoservice.findByHref(href);
		
		List<Videos> videos = videoservice.findAllVideosExcept(href);
		request.setAttribute("videos", videos);
		request.setAttribute("video", video);
		User currentUser= (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser!=null) {
			History history=historyservice.create(currentUser, video);
			request.setAttribute("flagLikedBtn", history.getIsliked());
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/views/user/detailvideo.jsp");
		requestDispatcher.forward(request, response);
	}
	protected void dogetLike(HttpSession session,String href,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User currentUser= (User) session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result=historyservice.updateLikeOrUnLike(currentUser, href);
		if(result) {
			response.setStatus(204);//succeed but no data
		}
		else {
			response.setStatus(400);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
