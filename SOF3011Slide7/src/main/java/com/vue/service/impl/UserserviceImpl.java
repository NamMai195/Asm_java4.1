package com.vue.service.impl;

import java.util.List;

import com.vue.dao.UserDao;
import com.vue.dao.impl.UserDaoImpl;
import com.vue.entity.User;
import com.vue.service.UserService;

public class UserserviceImpl implements UserService {
	private UserDao dao = new UserDaoImpl();

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String Email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(Email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByEmail(username);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByNameAPass(username, password);
	}

	@Override
	public User resetPasswork(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSide) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSide);
	}

	@Override
	public User create(String id, String username, String password, String email) {
		User newUser = new User();
		newUser.setId(id);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setRole(Boolean.FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	public User delete(String id) {
		User user = dao.findById(id);
		return dao.delete(user);

	}

}
