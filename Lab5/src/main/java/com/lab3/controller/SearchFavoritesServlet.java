package com.lab3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab3.entity.Favorite;
import com.lab3.service.FavoriteService;
import com.lab3.service.imlp.FavoriteServiceImpl;

/**
 * Servlet implementation class c
 */
@WebServlet("/searchFavorites")
public class SearchFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FavoriteService favoritesercive = new FavoriteServiceImpl();
		List<Favorite> favorites = favoritesercive.findall();
		request.setAttribute("favorites", favorites);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy tham số tìm kiếm từ query string hoặc form
		String searchQuery = req.getParameter("searchQuery");
		FavoriteService favoritesercive = new FavoriteServiceImpl();
		// Nếu người dùng nhập vào searchQuery, chúng ta sẽ tìm kiếm theo các tiêu chí
		List<Favorite> favorites = new ArrayList();
		if (searchQuery != null && !searchQuery.trim().isEmpty()) {
			// Tìm kiếm theo tên người dùng, email hoặc ID (dựa trên searchQuery)
			favorites = favoritesercive.searchFavorites(searchQuery);
		}
		if (favorites==null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/index.jsp");
			dispatcher.forward(req, resp);
		}
		// Truyền dữ liệu vào request
		else {
			req.setAttribute("favorites", favorites);

			// Chuyển tiếp đến trang JSP để hiển thị kết quả
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/index.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
