package com.poly.controller.admin;

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
import com.poly.service.UserService;
import com.poly.service.VideoService;
import com.poly.service.impl.HistoryServiceImpl;
import com.poly.service.impl.UserserviceImpl;
import com.poly.service.impl.VideoServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "UserControllerOfAdmin", urlPatterns = { "/admin/user" })
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserserviceImpl();
    HistoryService historyservice = new HistoryServiceImpl();

    public UserController() {
        super();
    }

    private VideoService videoservice = new VideoServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String id = request.getParameter("id"); // Lấy id từ parameter

        switch (action) {
            case "list": {
                doGetList(request, response);
                break;
            }
            case "delete": {
                doGetDelete(id, response); // Truyền id vào hàm doGetDelete
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + action);
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                doPostAdd(request, response);
                break;
            }
            case "edit": {
                doPostEdit(request, response);
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + action);
        }
    }

    protected void doGetList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        RequestDispatcher reqd = request.getRequestDispatcher("/views/admin/Listuser.jsp");
        reqd.forward(request, response);
    }

    protected void doPostEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            // Lấy dữ liệu từ request
            String userId = request.getParameter("userId");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive")); // Lấy isActive

            // Tìm user trong database
            User user = userService.findById(userId);
            if (user == null) {
                response.setStatus(404); // Not Found
                return;
            }

            // Cập nhật thông tin user
            user.setUsername(username);
            if (!password.isEmpty()) { // Chỉ cập nhật mật khẩu nếu có nhập
                user.setPassword(password);
            }
            user.setEmail(email);
            user.setRole(isAdmin);
            user.setIsActive(isActive); // Cập nhật isActive

            // Lưu user đã cập nhật vào database
            User userUpdated = userService.update(user);

            if (userUpdated != null) {
                response.setStatus(204); // OK
            } else {
                response.setStatus(400); // Bad Request
            }
        } catch (Exception e) {
            response.setStatus(500); // Internal Server Error
            e.printStackTrace();
        }
    }

    protected void doPostAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            // Lấy dữ liệu từ request
            String userId = request.getParameter("userId");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive")); // Lấy isActive

            // Kiểm tra userId đã tồn tại chưa
            if (userService.findById(userId) != null) {
                response.setStatus(400); // Bad Request
                response.getWriter().write("{\"error\": \"UserId đã tồn tại!\"}"); 
                return;
            }

            // Tạo đối tượng User
            User user = new User();
            user.setId(userId); // Set userId cho user
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(isAdmin);
            user.setIsActive(isActive); // Set isActive cho user

            // Thêm user vào database
            User userAdded = userService.create(userId, username, password, email);
            

            if (userAdded != null) {
                response.setStatus(204); // Created
            } else {
                response.setStatus(400); // Bad Request
            }
        } catch (Exception e) {
            response.setStatus(500); // Internal Server Error
            e.printStackTrace();
        }
    }

    protected void doGetDelete(String id, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            // Xóa user khỏi database
            userService.delete(id);
            response.setStatus(204); // No Content
        } catch (Exception e) {
            response.setStatus(400); // Bad Request
        }
    }
    
}