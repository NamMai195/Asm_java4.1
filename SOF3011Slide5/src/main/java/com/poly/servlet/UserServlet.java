package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.bean.User;
import com.poly.utils.UserDAO;

@WebServlet({"/user/index","/user/delete","/user/create","/user/update","/user/edit/*"})
public class UserServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		User user = new User();
		
		String uri = req.getRequestURI();
		
		if(uri.contains("edit")) {
			
			String id = uri.substring(uri.lastIndexOf("/")+1);
			System.out.print("edit:"+ id);
			user = dao.findById(id);
			
		}else if(uri.contains("create")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());				
				dao.create(user);
				req.setAttribute("message", "Them moi thanh cong");
			}catch (Exception e) {
				req.setAttribute("message", "Them moi that bai");
			}
		}else if(uri.contains("update")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.update(user);
				req.setAttribute("message", "Update thanh cong");
			}catch (Exception e) {
				req.setAttribute("message", "Update that bai");
			}
		}else if(uri.contains("delete")) {
			try {
				String id = req.getParameter("id");
				dao.remove(id);
				req.setAttribute("message", "Xoa thanh cong");
			}catch (Exception e) {
				req.setAttribute("message", "Xoa that bai");
			}
		}
		
		req.setAttribute("form", user);
		req.setAttribute("items", dao.findAll());		
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
	}
}
