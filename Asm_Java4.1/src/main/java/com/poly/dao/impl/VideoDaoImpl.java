package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.VideoDao;
import com.poly.entity.User;
import com.poly.entity.Videos;

public class VideoDaoImpl extends AbstractDao<Videos> implements VideoDao {

	@Override
	public Videos findById(String id) {
		// TODO Auto-generated method stub
		return super.findById(Videos.class, id);
	}

	@Override
	public Videos findByHref(String href) {
		String sql = "SELECT o FROM Videos o WHERE o.href = ?0";
		return super.findOne(Videos.class, sql, href);
	}

	@Override
	public List<Videos> findAll() {
		// TODO Auto-generated method stub
		return super.findAll(Videos.class, true);
	}

	@Override
	public List<Videos> findAll(int pageNumber, int pageSide) {
		return super.findAll(Videos.class, true, pageNumber, pageSide);

	}

	

}
