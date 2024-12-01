package com.poly.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import com.poly.util.JpaUtil;

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
	public List<T> findAll(Class<T> clazz, boolean existIsActive) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE o.isActive = 1");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	// Tìm tất cả với phân trang
	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE o.isActive = 1");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	public List<T> findAll(Class<T> clazz) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
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
	public List<T> callStores(String nameStore, Map<String, Object> params) {
	    try {
	        EntityManager entityManager = JpaUtil.getEntityManager();
	        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(nameStore);
	        params.forEach((key, value) -> query.setParameter(key, value));
	        return (List<T>) query.getResultList(); // Cần kiểm tra kiểu dữ liệu trả về
	    } catch (Exception e) {
	        System.out.println("Error calling stored procedure: {}"+ nameStore+ e);
	        // ... xử lý lỗi, ví dụ: trả về danh sách rỗng
	        return Collections.emptyList(); 
	    } finally {
	        // Đóng EntityManager (nếu cần)
	    }
	}
	 public T findOneByJpql(String jpql, Object... params) {
	        EntityManager em = JpaUtil.getEntityManager();
	        try {
	            TypedQuery<T> query = em.createQuery(jpql, (Class<T>) Object.class);
	            for (int i = 0; i < params.length; i++) {
	                query.setParameter(i + 1, params[i]);
	            }
	            List<T> result = query.getResultList();
	            if (result.isEmpty()) {
	                return null;
	            }
	            return result.get(0);
	        } finally {
	            em.close();
	        }
	    }
	 public List<T> findManyByJpql(String jpql, Object... params) {
		    EntityManager em = JpaUtil.getEntityManager();
		    try {
		        TypedQuery<T> query = em.createQuery(jpql, (Class<T>) Object.class); 
		        if (params != null && params.length > 0) { // Kiểm tra nếu có tham số
		            for (int i = 0; i < params.length; i++) {
		                query.setParameter(i + 1, params[i]);
		            }
		        }
		        return query.getResultList();
		    } finally {
		        em.close();
		    }
		}
	 
}
