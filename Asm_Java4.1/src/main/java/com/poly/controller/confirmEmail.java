package com.poly.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.service.UserService;
import com.poly.service.impl.UserserviceImpl;

// ... (Các import)

@WebServlet({ "/confirmEmail", "/confirmEmailForChangePass" })
public class confirmEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userservice = new UserserviceImpl();

	// ... (Các biến và constructor)

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher req = request.getRequestDispatcher("views/user/emailConfirmation.jsp");
		req.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tokenFromRequest = request.getParameter("token");
		HttpSession session = request.getSession(false);

		if (tokenFromRequest != null && session != null) {
			User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			String storedToken = (String) session.getAttribute("emailConfirmationToken"); // Lấy token từ session

			if (tokenFromRequest.equals(storedToken)) {
				// Token hợp lệ
				String NewPass = (String) session.getAttribute("newPass");

				if (NewPass != null) {
					currentUser.setPassword(NewPass);

					userservice.update(currentUser);

					// Xóa token và email mới khỏi session
					session.removeAttribute("emailConfirmationToken");
					session.removeAttribute("newPass");
					session.removeAttribute(SessionAttr.CURRENT_USER);
					// Chuyển hướng đến trang thông báo thành công
					response.sendRedirect("index");
				} else {
					System.out.println("x");
					String newEmail = (String) session.getAttribute("newEmail");
					currentUser.setEmail(newEmail);
					userservice.update(currentUser);

					// Xóa token và email mới khỏi session
					session.removeAttribute("emailConfirmationToken");
					session.removeAttribute("newEmail");
					session.removeAttribute(SessionAttr.CURRENT_USER);
					// Chuyển hướng đến trang thông báo thành công
					response.sendRedirect("index");
				}

			} else {
				// Token không hợp lệ
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('Token không hợp lệ. Vui lòng kiểm tra lại email.');");
				out.println("history.back();"); // Quay lại trang trước đó
				out.println("</script>");
			}
		} else {
			// Token hoặc session không hợp lệ
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('Có lỗi xảy ra. Vui lòng thử lại sau.');");
			out.println("history.back();"); // Quay lại trang trước đó
			out.println("</script>");
		}
	}
}