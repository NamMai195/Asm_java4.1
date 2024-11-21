package com.poly.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory;

	// Tạo và trả về EntityManager
	public static EntityManager getEntityManager() {
		// Kiểm tra factory có null hoặc đã đóng hay không
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("asmjava4");
		}
		return factory.createEntityManager();
	}

	// Đóng EntityManagerFactory
	public static void shutDown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
