package com.lab3.dao;
package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.poly.util.JpaUtil;

public class AbstractDao<T> {

    // Tìm entity theo ID
    public T findById(Class<T> clazz, Object id) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            return entityManager.find(clazz, id);
        }
    }

    // Tìm tất cả entity
    public List<T> findAll(Class<T> clazz, boolean existIsActive) {
        String entityName = clazz.getSimpleName();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive) {
            jpql.append(" WHERE o.isActive = 1");
        }
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            TypedQuery<T> query = entityManager.createQuery(jpql.toString(), clazz);
            return query.getResultList();
        }
    }

    // Tìm tất cả với phân trang
    public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
        String entityName = clazz.getSimpleName();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive) {
            jpql.append(" WHERE o.isActive = 1");
        }
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            TypedQuery<T> query = entityManager.createQuery(jpql.toString(), clazz);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        }
    }

    // Tìm một entity bằng query
    public T findOne(Class<T> clazz, String jpql, Object... params) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            TypedQuery<T> query = entityManager.createQuery(jpql, clazz);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]); // Sửa chỉ số tham số bắt đầu từ 1
            }
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    // Tìm nhiều entity bằng query
    public List<T> findMany(Class<T> clazz, String jpql, Object... params) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            TypedQuery<T> query = entityManager.createQuery(jpql, clazz);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]); // Sửa chỉ số tham số bắt đầu từ 1
            }
            return query.getResultList();
        }
    }

    // Native query
    public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            Query query = entityManager.createNativeQuery(sql);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]); // Sửa chỉ số tham số bắt đầu từ 1
            }
            return query.getResultList();
        }
    }

    // Thêm mới entity
    public T create(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();
            entityManager.persist(entity);
            trans.commit();
            System.out.println("Create done");
            return entity;
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            System.out.println("Cannot insert: " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    // Cập nhật entity
    public T update(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();
            entityManager.merge(entity);
            trans.commit();
            System.out.println("Update done");
            return entity;
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            System.out.println("Cannot update: " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    // Xóa entity
    public T delete(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();
            entityManager.remove(entity);
            trans.commit();
            System.out.println("Delete done");
            return entity;
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            System.out.println("Cannot delete: " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}