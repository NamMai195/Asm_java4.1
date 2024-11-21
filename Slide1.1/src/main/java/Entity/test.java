package Entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class test {
public static void main(String[] args) {
	//nạp persistencen và tạo entyty managerfactiory
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	//tạo entyty manager chuẩn bị lập trình CSDL;
	EntityManager em=factory.createEntityManager();
	em.getTransaction().begin();
	try {
		em.persist(em); em.merge(em); em.remove(em);
		em.getTransaction().commit();
	} catch (Exception e) {
		// TODO: handle exception
		em.getTransaction().rollback();
	}
	User user=em.find(User.class,"Nam");
	em.close();
}
}
