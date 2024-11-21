package com.lab3.dao.impl;

import java.util.List;

import com.lab3.dao.AbstractDao;
import com.lab3.dao.UserDao;
import com.lab3.entity.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao{

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return findById(id);
	}

	@Override
	public User findByEmail(String Email) {
		// TODO Auto-generated method stub
		return findByEmail(Email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return findByUsername(username);
	}

	@Override
	public User findByNameAPass(String username, String password) {
		// TODO Auto-generated method stub
		return findByNameAPass(username, password);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSide) {
		// TODO Auto-generated method stub
		return findAll();
	}

	  @Override
	    public User findbytext(String text) {
		  String sql = "SELECT u FROM User u WHERE u.fullName LIKE :keyword OR u.email LIKE :keyword OR u.userId LIKE :keyword";

		  Object[] params = {"keyword", "%" + text + "%"};
	            return findOne(User.class, sql,params);  // Chỉ truyền tham số text
	        
	    }



}
