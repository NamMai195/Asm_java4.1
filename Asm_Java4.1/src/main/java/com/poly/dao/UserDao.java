package com.poly.dao;

import java.util.List;
import java.util.Map;

import com.poly.entity.User;

public interface UserDao {
	User findById(String id);

	User findByEmail(String Email);

	User findByUsername(String username);

	User findByNameAPass(String username, String password);

	List<User> findAll();

	List<User> findAll(int pageNumber, int pageSide);

	User create(User entity);

	User update(User entity);

	User delete(User entity);
	
	List<User> findUserLikedVideoByHref( Map<String , Object> params);

}
