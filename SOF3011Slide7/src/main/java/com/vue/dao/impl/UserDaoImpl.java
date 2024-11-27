package com.vue.dao.impl;

import java.util.List;

import com.vue.dao.AbstractDao;
import com.vue.dao.UserDao;
import com.vue.entity.User;



public class UserDaoImpl extends AbstractDao<User> implements UserDao{

	@Override
	public User findById(String id) {
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String Email) {
		String sql="SELECT o FROM User o WHERE o.email=?0";
		return super.findOne(User.class,sql, Email);
	}

	@Override
	public User findByUsername(String username) {
		String sql="SELECT o FROM Users o WHERE o.username=?0";
		return super.findOne(User.class,sql, username);
	}

	@Override
	public User findByNameAPass(String username, String password) {
		String sql="SELECT o FROM User o WHERE o.id=?0 AND o.password =?1";
		return super.findOne(User.class,sql, username,password);
	}

	@Override
	public List<User> findAll() {
		return super.findAll(User.class,true);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSide) {
		return super.findAll(User.class, true, pageNumber, pageSide);
		
	}
	

}
