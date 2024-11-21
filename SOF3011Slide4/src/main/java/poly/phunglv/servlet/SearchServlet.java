package poly.phunglv.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poly.phunglv.bean.User;
import poly.phunglv.bean.Favorite;
@WebServlet("/search/check")
public class SearchServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {						
				String username = req.getParameter("username");
				// Nạp persistence.xml và tạo EntityManagerFactory
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
				// Tạo EntityManager để bắt đầu làm việc với CSDL
				EntityManager em = emf.createEntityManager();
				try {
					em.getTransaction().begin(); // Bắt đầu Transaction
					// MÃ THAO TÁC					
					User user = em.find(User.class, username);
					System.out.print("User: "+user.getFullname());//test
					
					List<Favorite> favorites = user.getFavorites();
					for(Favorite f : favorites) {
						System.out.print("\n"+f.getId()+" - "+f.getUser().getFullname()+" - "+f.getLikeDate()+" - "+
								f.getVideo().getTitle());				
					}
					
					req.setAttribute("user", user);
					
					//User u = new User();
					//u = favorites.get(0);					
					
					req.setAttribute("favorites", favorites);
					em.getTransaction().commit(); // Chấp nhận kết quả thao tác
					
				} catch (Exception e) {
					em.getTransaction().rollback(); // Hủy thao tác
					System.out.println("Error: "+e);
				}
				em.close();
				emf.close();		
				req.getRequestDispatcher("/views/search.jsp").forward(req, resp);
	}
}
