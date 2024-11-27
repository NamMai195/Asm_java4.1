package poly.phunglv.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/ajax/fetch/upload")
public class UploadServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("photo");
		String name = part.getSubmittedFileName();//tên file
		long size = part.getSize();//kich thuoc file
		String type = part.getContentType();//kieu file
		
		String filename = req.getServletContext().getRealPath("/files/"+name);
		part.write(filename); //luu vao file tren server
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		String format ="{\"name\":\"%s\",\"type\":\"%s\",\"size\":%d}";
		String responseData = String.format(format, name,type,size);
		resp.getWriter().print(responseData);
		
		//out to see
		System.out.println(responseData);
		System.out.println(req.getServletContext().getRealPath("/files/"));
	}
}
