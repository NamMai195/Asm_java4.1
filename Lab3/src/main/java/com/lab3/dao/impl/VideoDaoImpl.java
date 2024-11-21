package com.lab3.dao.impl;

import java.util.List;

import com.lab3.dao.AbstractDao;
import com.lab3.dao.VideoDao;
import com.lab3.entity.Video;

public class VideoDaoImpl extends AbstractDao<Video> implements VideoDao {

    @Override
    public Video findById(String id) {
        // TODO Auto-generated method stub
        return findById(id);
    }

    @Override
    public List<Video> findByTitle(String title) {
        // TODO Auto-generated method stub
    	String sql="select o from Video o where o.title like '%?0%'";
        return findMany(Video.class, sql, title);
    }

    @Override
    public List<Video> findAll() {
        // TODO Auto-generated method stub
        return findAll();
    }

    @Override
    public List<Video> findAll(int pageNumber, int pageSide) {
        // TODO Auto-generated method stub
        return findAll(pageNumber, pageSide);
    }

    @Override
    public Video create(Video entity) {
        // TODO Auto-generated method stub
        return create(entity);
    }

    @Override
    public Video update(Video entity) {
        // TODO Auto-generated method stub
        return update(entity);
    }

    @Override
    public Video delete(Video entity) {
        // TODO Auto-generated method stub
        return delete(entity);
    }
}
