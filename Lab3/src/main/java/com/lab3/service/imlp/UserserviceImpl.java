package com.lab3.service.imlp;

import java.util.List;

import com.lab3.dao.UserDao;
import com.lab3.dao.impl.UserDaoImpl;
import com.lab3.entity.User;
import com.lab3.service.UserService;



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
		return dao.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByNameAPass(username, password);
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
	public User create(String username, String password, String email) {
		User newUser = new User();
		newUser.setFullName(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setAdmin(Boolean.FALSE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}
	@Override
	public User delete(String id) {
		User user = dao.findById(id);
		return dao.delete(user);

	}

	@Override
	public User findbytext(String text) {
		// TODO Auto-generated method stub
		return dao.findbytext(text);
	}

	

}
