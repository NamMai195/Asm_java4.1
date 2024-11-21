package poly.phunglv.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XJPA {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("PolyOE");
	}
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
