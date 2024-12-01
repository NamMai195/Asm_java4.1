package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.ShareDao;
import com.poly.entity.Share;
import com.poly.entity.Videos;

public class ShareDaoImpl extends AbstractDao<Share> implements ShareDao {

    @Override
    public List<Share> findAll() {
        // Tìm tất cả các Share có trạng thái isActive = true
        return super.findAll(Share.class);
    }

	@Override
	public Share findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Share create(Share entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public Share update(Share entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Share delete(Share href) {
		// TODO Auto-generated method stub
		return super.delete(href);
	}

}
