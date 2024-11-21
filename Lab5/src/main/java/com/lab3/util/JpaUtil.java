package com.lab3.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory factory;

    // Tạo và trả về EntityManager
    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            synchronized (JpaUtil.class) {
                if (factory == null || !factory.isOpen()) {
                    try {
                        factory = Persistence.createEntityManagerFactory("PolyOE");
                    } catch (Exception e) {
                        // Xử lý exception, ví dụ: log lỗi, throw exception
                        e.printStackTrace(); 
                        throw new RuntimeException("Lỗi khi tạo EntityManagerFactory", e);
                    }
                }
            }
        }
        return factory.createEntityManager(); // Nhớ đóng EntityManager sau khi sử dụng
    }

    // Đóng EntityManagerFactory
    public static void shutDown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
        factory = null;
    }
}