package com.vue.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vue.entity.User;
import com.vue.service.UserService;
import com.vue.service.impl.UserserviceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userapi/users/*")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserserviceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper(); // Dùng để chuyển đổi JSON

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Lấy danh sách người dùng
                List<User> users = userService.findAll();
                resp.getWriter().write(objectMapper.writeValueAsString(users)); // Chuyển danh sách thành JSON
            } else {
                // Tìm user theo ID
                String id = pathInfo.substring(1);
                User user = userService.findById(id);
                if (user != null) {
                    resp.getWriter().write(objectMapper.writeValueAsString(user));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"message\": \"User not found\"}");
                }
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"An error occurred\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            User newUser = objectMapper.readValue(req.getReader(), User.class); // Parse JSON request thành User
            User createdUser = userService.create(newUser.getId(), newUser.getUsername(), newUser.getPassword(), newUser.getEmail());
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(objectMapper.writeValueAsString(createdUser));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"Invalid data\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"User ID is required\"}");
            return;
        }

        String id = pathInfo.substring(1);

        try {
            User existingUser = userService.findById(id);
            if (existingUser == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"message\": \"User not found\"}");
                return;
            }

            User updatedData = objectMapper.readValue(req.getReader(), User.class); // Parse JSON request
            existingUser.setUsername(updatedData.getUsername());
            existingUser.setPassword(updatedData.getPassword());
            existingUser.setEmail(updatedData.getEmail());

            User updatedUser = userService.update(existingUser);
            resp.getWriter().write(objectMapper.writeValueAsString(updatedUser));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"Invalid data\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"User ID is required\"}");
            return;
        }

        String id = pathInfo.substring(1);

        try {
            User deletedUser = userService.delete(id);
            if (deletedUser != null) {
                resp.getWriter().write(objectMapper.writeValueAsString(deletedUser));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"message\": \"User not found\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"An error occurred\"}");
        }
    }
}
