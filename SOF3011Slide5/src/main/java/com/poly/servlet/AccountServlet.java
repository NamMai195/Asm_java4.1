package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.bean.User;
import com.poly.utils.UserDAO;

@WebServlet({"/account/sign-up", "/account/sign-in", "/account/sign-out","/account/edit-profile"})
public class AccountServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("sign-in")) {
			this.doSignIn(req, resp);
		}
		else if(uri.contains("sign-up")) {
			this.doSignUp(req, resp);
		}
		else if(uri.contains("sign-out")) {
			req.getSession().setAttribute("user", null);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else if(uri.contains("forgot-password")) {}
		else if(uri.contains("change-password")) {}
		else if(uri.contains("edit-profile")) {
			this.doEditProfile(req, resp);
		}
	}
	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("username");
			String pw = req.getParameter("password");
			// TODO: ĐĂNG NHẬP
			try {
				UserDAO dao = new UserDAO();
				User user = dao.findById(id);
				if(!user.getPassword().equals(pw)) {
					req.setAttribute("message", "Sai mật khẩu!");
				}
				else {
					req.setAttribute("message", "Đăng nhập thành công!");
					req.getSession().setAttribute("user", user);
					req.setAttribute("reqUser", user);			
					req.getRequestDispatcher("/user/index").forward(req, resp);
				}
			} catch (Exception e) {
				req.setAttribute("message", "Sai tên đăng nhập!");
			}
		}
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equalsIgnoreCase("POST")) {
			// TODO: ĐĂNG KÝ
			try {
				User entity = new User();
				BeanUtils.populate(entity, req.getParameterMap());

				UserDAO dao = new UserDAO();
				dao.create(entity);
				req.setAttribute("message", "Đăng ký thành công!");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi đăng ký!");
			}
		}
		req.getRequestDispatcher("/views/account/sign-up.jsp").forward(req, resp);
	}
	private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String method = req.getMethod();
		if(method.equalsIgnoreCase("POST")) {
			// TODO: CẬP NHẬT
			try {
				BeanUtils.populate(user, req.getParameterMap());

				UserDAO dao = new UserDAO();
				dao.update(user);
				req.setAttribute("message", "Cập nhật tài khoản thành công!");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi cập nhật tài khoản!");
			}

		}
		req.getRequestDispatcher("/views/account/edit-profile.jsp").forward(req, resp);
	}

}
