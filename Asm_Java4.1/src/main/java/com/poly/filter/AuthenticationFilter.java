package com.poly.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;

import java.io.IOException;

@WebFilter(urlPatterns = {"/favorite", "/history","/edituser","/changepass"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter (nếu cần)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        Object currentUser = session.getAttribute(SessionAttr.CURRENT_USER); 
        if (currentUser != null) {
            // Người dùng đã đăng nhập, cho phép truy cập
            chain.doFilter(request, response);
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            res.sendRedirect(req.getContextPath() + "/login"); 
        }
    }

    @Override
    public void destroy() {
        // Hủy filter (nếu cần)
    }
}
