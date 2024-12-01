package com.poly.service.impl;

import java.util.List;

import com.poly.dao.HistoryDao;
import com.poly.dao.ShareDao;
import com.poly.dao.impl.HistoryDaoImpl;
import com.poly.dao.impl.ShareDaoImpl;
import com.poly.entity.Share;
import com.poly.entity.Videos;
import com.poly.service.ShareService;

public class ShareServiceImpl implements ShareService{
	private ShareDao dao;

	public ShareServiceImpl() {
		dao = new ShareDaoImpl();
	}
	@Override
	public Share findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Share> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Share create(Share entity) {
		// TODO Auto-generated method stub
		return dao.create(entity);
	}

	@Override
	public Share update(Share entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public Share delete(Share href) {
		// TODO Auto-generated method stub
		return dao.delete(href);
	}

}
