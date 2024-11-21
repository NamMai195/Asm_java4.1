package com.lab3.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lab3.util.JpaUtil;


public class AbstractDao<T> {

	// Tìm entity theo ID
	public T findById(Class<T> clazz, Object id) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			return entityManager.find(clazz, id);
		} finally {
			entityManager.close();
		}
	}

	// Tìm tất cả entity
	public List<T> findAll(Class<T> clazz) {  // Bỏ tham số existIsActive
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			// Bỏ điều kiện if (existIsActive)
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	// Tìm tất cả với phân trang
	public List<T> findAll(Class<T> clazz, int pageNumber, int pageSize) {  // Bỏ tham số existIsActive
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			// Bỏ điều kiện if (existIsActive)
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	// Tìm một entity bằng query
	public T findOne(Class<T> clazz, String sql, Object... params) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]); // Giữ nguyên chỉ số tham số bắt đầu từ 0
			}
			List<T> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			entityManager.close();
		}
	}

	// Tìm nhiều entity bằng query
	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]); // Giữ nguyên chỉ số tham số bắt đầu từ 0
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	// Native query
	@SuppressWarnings("unchecked")
	public List<Object[]> findManyByNativeQuery(Class<T> clazz, String sql, Object... params) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			Query query = entityManager.createNativeQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]); // Giữ nguyên chỉ số tham số bắt đầu từ 0
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			Query query = entityManager.createNativeQuery(sql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	// Thêm mới entity
	public T create(T entity) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			System.out.println("Create done");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot insert: " + entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	// Cập nhật entity
	public T update(T entity) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			System.out.println("Update done");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot update: " + entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	// Xóa entity
	public T delete(T entity) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			System.out.println("Delete done");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot delete: " + entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}
}