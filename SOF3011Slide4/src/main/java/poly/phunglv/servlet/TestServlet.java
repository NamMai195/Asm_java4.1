package poly.phunglv.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import poly.phunglv.bean.Auth;
import poly.phunglv.bean.Favorite;
import poly.phunglv.bean.Report;
import poly.phunglv.bean.User;
import poly.phunglv.bean.Video;
import poly.phunglv.utils.XJPA;

@WebServlet("/")
public class TestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//findAll();
			
		/*
		User u = findOne("teolv");
		System.out.println("-----User Info -------");
		System.out.println("- ID: "+u.getId());
		System.out.println("- Fullname: "+u.getFullname());
		System.out.println("- Email: "+u.getEmail());
		System.out.println("- Password: "+u.getPassword());
		*/
		
		listFavorite(2020, 2024);		
		listVideo("teolv");		
		listObjectToEntity();
		
		/*
		User entity = new User();
		entity.setId("ID005");
		entity.setFullname("Le Phung Hieu Kien ");
		entity.setEmail("anlph@gmail.com");
		entity.setPassword("123456");
		create(entity);
		*/
		
		//delete();		
		//findNameQuery();		
		//nativeSQL();		
		//CalProc();
					
	}
	
	private static void findAll() {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			//Câu lệnh JPQL truy vấn thực thể
			String jpql = "SELECT o FROM User o";
			//Tạo Query/TypedQuery<T> để truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			//Truy vấn danh sách thực thể
			List<User> list = query.getResultList();
			System.out.println("--------------------------------");
			for(User user : list) {
				System.out.println(user.getId()+"\t"+user.getFullname()+"\t"+user.getEmail());
			}
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();
	}
	//Truy vấn các yêu thích trong khoảng thời gian
	private static void listFavorite(int tu, int den) {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); 
			
			String jpql = "SELECT o FROM Favorite o WHERE year(o.likeDate) BETWEEN ?0 AND ?1";
			TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
			query.setParameter(0, tu);
			query.setParameter(1, den);
			List<Favorite> likes = query.getResultList();
			for(Favorite f : likes) {
				System.out.print("\n"+f.getId()+" - "+f.getUser().getFullname()+" - "+f.getLikeDate()+" - "+
						f.getVideo().getTitle());				
			}
									
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();
	}
	//Truy van cac video duoc yeu thich boi User co id ???
	private static void listVideo(String id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); 
					
			String jpql ="SELECT o.video FROM Favorite o WHERE o.user.id =:uid";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("uid", id);
			List<Video> videos = query.getResultList();
			for(Video v : videos) {
				System.out.print("\n"+v.getId() +" - "+v.getTitle()+" - "+v.getPoster()+" - "+
						v.getViews()+" - "+v.getActive());				
			}	
									
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback(); 
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();
	}
	//Chuyển đổi List<Object[]> thành List<Auth>
	private static void listObjectToEntity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); 
			/*
			String jpql ="SELECT o.id, o.password FROM User o";					
			TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			List<Object[]> list = query.getResultList();			
			for(Object[] rp : list) {
				System.out.print("\n"+rp[0]+" - "+ rp[1]);				
			}
			*/
			
			//Chuyển đổi List<Object[]> thành List<Auth>
			String jpql ="SELECT new Auth(o.id, o.password) FROM User o";					
			TypedQuery<Auth> query = em.createQuery(jpql, Auth.class);
			List<Auth> list = query.getResultList();
			for(Auth rp : list) {
				System.out.print("\n"+rp.getUsername() +" - "+ rp.getPassword());				
			}
									
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback(); 
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();
	}
	private static void jpqlHam() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); 
			
			String jpql ="SELECT o.id, o.password FROM User o";					
			TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			List<Object[]> list = query.getResultList();			
			for(Object[] rp : list) {
				System.out.print("\n"+rp[0]+" - "+ rp[1]);				
			}

			/*
			//Chuyển đổi List<Object[]> thành List<Auth>
			String jpql ="SELECT new Auth(o.id, o.password) FROM User o";					
			TypedQuery<Auth> query = em.createQuery(jpql, Auth.class);
			List<Auth> list = query.getResultList();
			for(Auth rp : list) {
				System.out.print("\n"+rp.getUsername() +" - "+ rp.getPassword());				
			}
			*/						
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback(); 
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();
	}
	
	public User findOne(String id) {
		EntityManager em = XJPA.getEntityManager();
		try {
			em.getTransaction().begin();
			
			String jpql = "SELECT o FROM User o WHERE o.id=?0"; 
			TypedQuery<User> query = em.createQuery(jpql, User.class); 
			query.setParameter(0, id); 
			User user = query.getSingleResult(); 
			
			em.getTransaction().commit();
			em.close();
			return user;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}	
	}
	public User create(User entity) {
		EntityManager em = XJPA.getEntityManager();
		try {
			em.getTransaction().begin();						
			em.persist(entity);
			em.getTransaction().commit();
			em.close();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}	
	}
	private static void delete() {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			String jpql = "DELETE FROM User o WHERE o.id = :ids";
			Query query = em.createQuery(jpql);
			query.setParameter("ids", "ti 2");
			int deletedCount = query.executeUpdate();
			
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();		
	}
	//@NamedQuery
	private static void findNameQuery() {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			//............ @NamedQuery .................			
			TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
			List<User> list = query.getResultList();
			for(User u : list) {
				System.out.print("\n"+u.getId() +"\t"+u.getFullname()+"\t"+u.getEmail()+"\t"+
						u.getAdmin()+"\t"+u.getPassword());				
			}	
			
			em.getTransaction().commit(); 
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error: "+e);
		}
		em.close();
		emf.close();
	}
	//@NativeSQL
		private static void nativeSQL() {
			// Nạp persistence.xml và tạo EntityManagerFactory
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin(); // Bắt đầu Transaction
				
				// SQL - truy van dac thu
				String sql = "SELECT * FROM Users WHERE email LIKE ?";
				Query query  = em.createNativeQuery(sql, User.class);
				query.setParameter(1, "%@gmail.com");
				List<User> list = query.getResultList();
				for(User u : list) {
					System.out.print("\n"+u.getId() +" \t"+u.getFullname()+"\t"+u.getPassword() +"\t"+
							u.getAdmin()+"\t"+u.getEmail());				
				}
				
				em.getTransaction().commit(); 
			} catch (Exception e) {
				em.getTransaction().rollback();
				System.out.println("Error: "+e);
			}
			em.close();
			emf.close();
		}
		
		private static void CalProc() {
			// Nạp persistence.xml và tạo EntityManagerFactory
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
			// Tạo EntityManager để bắt đầu làm việc với CSDL
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin(); // Bắt đầu Transaction
				
				//-------Goi Procedure ---------------------
				StoredProcedureQuery query = 
				em.createNamedStoredProcedureQuery("User.spFindByEmail");
				query.setParameter("email", "%@gmail.com");
				List<User> list = query.getResultList();
				for(User u : list) {
					System.out.print("\n"+u.getId() +" \t"+u.getFullname()+"\t"+u.getPassword() +"\t"+
							u.getAdmin()+"\t"+u.getEmail());				
				}
				/*
				int year  = 2023;
				StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Report.favoriteByYear");
				query.setParameter("year", year);
				List<Report> list = query.getResultList();
				for(Report r : list) {
					System.out.print("\n"+r.getGroup() +" \t"+r.getLikes()+"\t"+r.getOldest() +"\t"+r.getNewest());				
				}
				*/
				em.getTransaction().commit(); 
			} catch (Exception e) {
				em.getTransaction().rollback();
				System.out.println("Error: "+e);
			}
			em.close();
			emf.close();
		}
}
