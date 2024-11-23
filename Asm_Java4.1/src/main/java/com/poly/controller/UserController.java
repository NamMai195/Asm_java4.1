package com.poly.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.websocket.Session;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.service.EmailService;
import com.poly.service.UserService;
import com.poly.service.impl.EmailServiceImpl;
import com.poly.service.impl.UserserviceImpl;

import com.poly.util.StringUtils;
import com.poly.util.TokenGenerator;

/**
 * Servlet implementation class UserController
 */
@WebServlet({ "/login", "/logout", "/register", "/forgot", "/edituser", "/changepass" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private UserService userservice = new UserserviceImpl();
	private EmailService emailservice = new EmailServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		switch (path) {
		case "/login": {
			doGetLogin(request, response);
			break;
		}
		case "/logout": {
			dogetLogout(request, response);
			break;
		}
		case "/register": {
			doGetRes(request, response);
			break;
		}
		case "/forgot": {
			doGetForgot(request, response);
			break;
		}
		case "/edituser": {
			doGetEditUser(request, response);
			break;
		}
		case "/changepass": {
			doGetChangepass(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	protected void doGetLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher req = request.getRequestDispatcher("views/user/login.jsp");
		req.forward(request, response);
	}

	protected void doGetRes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher req = request.getRequestDispatcher("views/user/res.jsp");
		req.forward(request, response);
	}

	protected void doGetForgot(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher req = request.getRequestDispatcher("views/user/forgotpass.jsp");
		req.forward(request, response);
	}

	protected void doGetEditUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher req = request.getRequestDispatcher("views/user/edituser.jsp");
		req.forward(request, response);
	}

	protected void doGetChangepass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher req = request.getRequestDispatcher("views/user/changePassFrom.jsp");
		req.forward(request, response);
	}

	protected void dogetLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionAttr.CURRENT_USER);
		response.sendRedirect("index");
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		switch (path) {
		case "/login": {
			doPostlogin(session, request, response);
			break;
		}
		case "/register": {
			doPostRes(session, request, response);
			break;
		}
		case "/forgot": {
			doPostForgot(session, request, response);
			break;
		}
		case "/edituser": {
			doPostEditUser(session, request, response);
			break;
		}
		case "/changepass": {
			doPostchangepass(session, request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	protected void doPostlogin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userservice.login(username, password);
		if (user != null) {

			session.setAttribute("currentUser", user);
			response.sendRedirect("index");

		} else {
			System.out.println("Thất Bại");
			response.sendRedirect("login");
		}
	}

	protected void doPostRes(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		User user = userservice.findById(id);
		if (user == null) {
			try {

				userservice.create(id, fullName, password, email);
				User usermail = new User();
				usermail.setId(id);
				usermail.setEmail(email);
				usermail.setPassword(password);
				usermail.setUsername(fullName);
				emailservice.sendMail(getServletContext(), usermail, "welcome");
				response.sendRedirect("login");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("Thất Bại");
			response.sendRedirect("register");
		}

	}

	protected void doPostForgot(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		User user = userservice.findByEmail(email);
		if (user != null) {
			try {
				emailservice.sendMail(getServletContext(), user, "forgot");
				response.sendRedirect("login");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("Thất Bại");
			response.sendRedirect("forgot");
		}

	}

	protected void doPostchangepass(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		if (currentUser.getPassword().equals(oldPassword)) {
			String token = TokenGenerator.generateToken(10);
			// Lưu token vào session
			session.setAttribute("emailConfirmationToken", token);
			// Lưu email mới vào session
			session.setAttribute("newPass", newPassword);

			emailservice.sendMail(getServletContext(), currentUser, "changepass", newPassword, token);

			// Chuyển hướng đến trang xác nhận email
			response.sendRedirect("confirmEmail");

		} else {
			// Không có thay đổi nào được thực hiện
			// ... (Thêm xử lý, ví dụ: hiển thị thông báo hoặc chuyển hướng)
		}
	}

	protected void doPostEditUser(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		String newEmail = request.getParameter("email");
		String newFullName = request.getParameter("fullname");

		if (!currentUser.getEmail().equals(newEmail)) {
			// Xử lý thay đổi email
			User user = userservice.findByEmail(newEmail);
			if (user == null) {
				// Email mới chưa tồn tại, gửi email xác nhận
				String token = TokenGenerator.generateToken(10);

				// Lưu token vào session
				session.setAttribute("emailConfirmationToken", token);

				// Lưu email mới vào session
				session.setAttribute("newEmail", newEmail);

				emailservice.sendMail(getServletContext(), currentUser, "update", newEmail, token);

				// Chuyển hướng đến trang xác nhận email
				response.sendRedirect("confirmEmail");
			} else {
				// Xử lý lỗi email đã tồn tại
				System.out.println("Email đã tồn tại");
				// ... (Thêm xử lý lỗi, ví dụ: hiển thị thông báo lỗi cho người dùng)
			}
		} else if (!currentUser.getUsername().equals(newFullName)) {
			// Xử lý thay đổi fullname (nếu có)
			currentUser.setUsername(newFullName);
			userservice.update(currentUser);
			session.removeAttribute(SessionAttr.CURRENT_USER);
			response.sendRedirect("index");
		} else {
			// Không có thay đổi nào được thực hiện
			// ... (Thêm xử lý, ví dụ: hiển thị thông báo hoặc chuyển hướng)
		}
	}

}
