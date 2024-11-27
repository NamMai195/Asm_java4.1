package poly.phunglv.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ajax/fetch/text","/ajax/fetch/json"})
public class AjaxFetchServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if(uri.contains("text")) {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/plain");
			String data = "Công cha như núi Tháo sơn";
			resp.getWriter().print(data);
		}
		else if(uri.contains("json")) {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			String data = "{\"name\":\"Tuấn\",\"age\":20,\"gender\":true}";
			resp.getWriter().print(data);
		}
		
		
	}
}
