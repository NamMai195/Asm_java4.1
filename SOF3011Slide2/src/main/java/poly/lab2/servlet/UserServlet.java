package poly.lab2.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import poly.lab2.bean.User;
import poly.lab2.dao.UserDAO;
@WebServlet({"/", "/user/index", "/user/delete", "/user/create", "/user/update", "/user/edit/*", "/user/search"})
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        User user = new User();

        String uri = req.getRequestURI();

        if (uri.contains("edit")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            user = dao.findById(id);

        } else if (uri.contains("create")) {
            try {
                BeanUtils.populate(user, req.getParameterMap());
                dao.create(user);
                req.setAttribute("message", "Thêm mới thành công");
            } catch (Exception e) {
                req.setAttribute("message", "Thêm mới thất bại");
            }
        } else if (uri.contains("update")) {
            try {
                BeanUtils.populate(user, req.getParameterMap());
                dao.update(user);
                req.setAttribute("message", "Cập nhật thành công");
            } catch (Exception e) {
                req.setAttribute("message", "Cập nhật thất bại");
            }
        } else if (uri.contains("delete")) {
            try {
                String id = req.getParameter("id");
                dao.remove(id);
                req.setAttribute("message", "Xóa thành công");
            } catch (Exception e) {
                req.setAttribute("message", "Xóa thất bại");
            }
        } else if (uri.contains("search")) {
            String keyword = req.getParameter("keyword");
      
            List<User> list = dao.searchByKeyword(keyword);
            req.setAttribute("items", list);
            req.setAttribute("message", "Tìm kiếm thành công");
        } else {
            user = new User();
        }

        req.setAttribute("form", user);
        if (!uri.contains("search")) {
            req.setAttribute("items", dao.findAll());
        }

        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }
}
