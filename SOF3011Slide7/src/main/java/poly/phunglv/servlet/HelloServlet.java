package poly.phunglv.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/*")
public class HelloServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod());

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		String data = "{\"get\": true}";
		resp.getWriter().print(data);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod());
		System.out.println(this.readData(req));
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		String data = "{\"post\": true}";
		resp.getWriter().print(data);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod() + ": " + req.getPathInfo().substring(1));
		System.out.println(this.readData(req));
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		String data = "{\"put\": true}";
		resp.getWriter().print(data);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod() + ": " + req.getPathInfo().substring(1));
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		String data = "{\"delete\": true}";
		resp.getWriter().print(data);
	}
	
	String readData(HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("utf-8");
		BufferedReader reader = req.getReader();
		String line;
		StringBuffer buffer = new StringBuffer();
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		return buffer.toString();
	}
}