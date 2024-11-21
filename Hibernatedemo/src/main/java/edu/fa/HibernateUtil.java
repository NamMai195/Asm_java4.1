package edu.fa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import edu.fa.model.Course;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Load the configuration and create ServiceRegistry
            Configuration configuration = new Configuration().configure();
            
            // Thêm lớp Course vào cấu hình
            configuration.addAnnotatedClass(Course.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // Create the SessionFactory from the ServiceRegistry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void main(String[] args) {
        // Thực hiện thao tác với database trong một phương thức riêng
        saveCourse(new Course(0,"Hibernate"));
    }

    private static void saveCourse(Course course) {
        // Mở một Session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Bắt đầu giao dịch
            session.beginTransaction();
            // Lưu course
            session.save(course);
            // Commit giao dịch
            session.getTransaction().commit();
        } catch (Exception e) {
            // Nếu có lỗi, rollback giao dịch
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // In ra lỗi
        } finally {
            // Đóng session
            session.close();
        }
    }
}
