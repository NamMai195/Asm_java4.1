package com.lab3.dao.impl;

import java.util.List;

import com.lab3.dao.AbstractDao;
import com.lab3.dao.ShareDao;
import com.lab3.entity.Share;

public class ShareDaoImpl extends AbstractDao<Share> implements ShareDao {

    @Override
    public Share findById(String id) {
        // TODO Auto-generated method stub
        return findById(id);
    }

    @Override
    public List<Share> findByUserId(String userId) {
        // TODO Auto-generated method stub
    	String sql="SELECT s FROM Share s WHERE s.user.userId = ?1";
        return findMany(Share.class,sql, userId);
    }

    @Override
    public List<Share> findByVideoId(String videoId) {
        // TODO Auto-generated method stub
    	String sql="SELECT s FROM Share s WHERE s.video.videoId = ?1";
        return findMany(Share.class,sql, videoId);
    }

    @Override
    public List<Share> findAll() {
        // TODO Auto-generated method stub
        return findAll();
    }

    @Override
    public List<Share> findAll(int pageNumber, int pageSide) {
        // TODO Auto-generated method stub
        return findAll(pageNumber, pageSide);
    }

    @Override
    public Share create(Share entity) {
        // TODO Auto-generated method stub
        return create(entity);
    }

    @Override
    public Share update(Share entity) {
        // TODO Auto-generated method stub
        return update(entity);
    }

    @Override
    public Share delete(Share entity) {
        // TODO Auto-generated method stub
        return delete(entity);
    }

	@Override
	public List<Share> findByEmails(String emails) {
		// TODO Auto-generated method stub
		return null;
	}
}
