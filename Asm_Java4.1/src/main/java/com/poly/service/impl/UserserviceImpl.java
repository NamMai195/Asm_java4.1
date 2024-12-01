package com.poly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.poly.dao.HistoryDao;
import com.poly.dao.UserDao;
import com.poly.dao.impl.HistoryDaoImpl;
import com.poly.dao.impl.UserDaoImpl;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.service.UserService;

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

		user.setIsActive(Boolean.FALSE);
		return dao.update(user);

	}

	@Override
	public List<User> findUserLikedByHref(String href) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap();
		params.put("videohref", href);

		return dao.findUserLikedVideoByHref(params);
	}

}
