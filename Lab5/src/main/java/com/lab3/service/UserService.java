package com.lab3.service;

import java.util.List;

import com.lab3.entity.User;



public interface UserService {
	User findById(String id);

	User findByEmail(String Email);

	User findByUsername(String username);

	User login(String username, String password);

	List<User> findAll();

	List<User> findAll(int pageNumber, int pageSide);

	User create( String username, String password, String email);

	User update(User entity);

	User delete(String id);
    
	User findbytext(String text);
}
