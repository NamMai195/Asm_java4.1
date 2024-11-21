package poly.phunglv.servlet;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import poly.phunglv.bean.User;
import poly.phunglv.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/","/user/index","/user/delete","/user/create","/user/update","/user/edit/*"})
public class UserServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		User user = new User();
		
		String uri = req.getRequestURI();
		
		//List<User> list = dao.findAll();
		//for(User u : list) {
			//System.out.println(u.getId()+"\t"+u.getFullname()+"\t"+u.getAdmin());
		//}
		//user = list.get(0);
		
		if(uri.contains("edit")) {
			
			String id = uri.substring(uri.lastIndexOf("/")+1);
			System.out.println("edit:"+ id);
			user = dao.findById(id);
			
		}else if(uri.contains("create")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());				
				dao.create(user);
				req.setAttribute("message", "Thêm mới thành công");
			}catch (Exception e) {
				req.setAttribute("message", "Thêm mới thất bại");
			}
		}else if(uri.contains("update")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.update(user);
				req.setAttribute("message", "Cập nhật thành công");
			}catch (Exception e) {
				req.setAttribute("message", "Cập nhật thất bại");
			}
		}else if(uri.contains("delete")) {
			try {
				String id = req.getParameter("id");
				dao.remove(id);
				req.setAttribute("message", "Xóa thành công");
			}catch (Exception e) {
				req.setAttribute("message", "Xóa thất bại");
			}
		}else {
			user = new User();
		}
		
		req.setAttribute("form", user);
		req.setAttribute("items", dao.findAll());		
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
		//req.getRequestDispatcher("/views/user2.jsp").forward(req, resp);
	}
}
