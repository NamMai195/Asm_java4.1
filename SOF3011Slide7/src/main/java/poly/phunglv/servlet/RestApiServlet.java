package poly.phunglv.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rest/api/crud/*")
public class RestApiServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
		System.out.println(this.readJson(req));
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}

private String readJson(HttpServletRequest req) throws IOException {
	req.setCharacterEncoding("utf-8");
	BufferedReader reader = req.getReader();
	String line;
	StringBuffer buffer = new StringBuffer();
	while((line = reader.readLine()) != null) {
		buffer.append(line).append("\r\n");
	}
	reader.close();
	return buffer.toString();
}

private void sendJson(HttpServletResponse resp, String json) throws IOException {
	resp.setCharacterEncoding("utf-8");
	resp.setContentType("application/json");
	resp.getWriter().print(json);
}
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getMethod();
		String servletPath = req.getServletPath();
		String pathInfo = req.getPathInfo();
		String fmt = "{\"method\":\"%s\", \"servlet-path\":\"%s\", \"path-info\":\"%s\"}";
		String json = String.format(fmt, method, servletPath, pathInfo);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.getWriter().print(json);
	}
}
