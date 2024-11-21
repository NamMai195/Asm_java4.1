package com.poly.service;

import java.util.List;

import com.poly.entity.User;

public interface UserService {
	User findById(String id);

	User findByEmail(String Email);

	User findByUsername(String username);

	User login(String username, String password);

	User resetPasswork(String email);

	List<User> findAll();

	List<User> findAll(int pageNumber, int pageSide);

	User create(String id,String username, String password,String email);

	User update(User entity);

	User delete(String username);

}
