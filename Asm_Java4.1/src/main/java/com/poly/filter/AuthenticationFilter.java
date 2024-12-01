package com.poly.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/favorite", "/history","/edituser","/changepass", "/admin/*","/Homeadmin/*"}) // Add /admin/*
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization (if needed)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Object currentUser = session.getAttribute(SessionAttr.CURRENT_USER);

        if (req.getServletPath().startsWith("/admin/") || req.getServletPath().startsWith("/Homeadmin/") ) {
            // Request to admin pages
            if (currentUser != null && ((User) currentUser).getRole()) {
                // User is logged in and is an admin, allow access
                chain.doFilter(request, response);
            } else {
                // User is not logged in or is not an admin, redirect to login page
                res.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            // Request to user pages
            if (currentUser != null) {
                // User is logged in, allow access
                chain.doFilter(request, response);
            } else {
                // User is not logged in, redirect to login page
                res.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {
        // Destroy filter (if needed)
    }
}