package com.poly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.poly.dao.VideoDao;
import com.poly.dao.impl.VideoDaoImpl;
import com.poly.entity.Videos;
import com.poly.service.VideoService;

public class VideoServiceImpl implements VideoService {
    private VideoDao videoDao = new VideoDaoImpl(); // Khởi tạo DAO

    @Override
    public Videos findById(String id) {
        return videoDao.findById(id); // Đảm bảo phương thức này khớp với phương thức của VideoService
    }

    @Override
    public Videos findByHref(String href) {
        return videoDao.findByHref(href);
    }

    @Override
    public List<Videos> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Videos> findAll(int pageNumber, int pageSize) {
        return videoDao.findAll(pageNumber, pageSize);
    }

    @Override
    public Videos create(Videos entity) {
    	entity.setIsActive(Boolean.TRUE);
    	entity.setViews(0);
        return videoDao.create(entity);
    }

    @Override
    public Videos update(Videos entity) {
        return videoDao.update(entity);
    }

    @Override
    public Videos delete(String href) {
    	Videos entity=findByHref(href);
    	
        return videoDao.delete(entity);
    }

	@Override
//	public List<Videos> findAllVideosExcept(String href) {
//		// TODO Auto-generated method stub
//		List<Videos> allvideo=videoDao.findAll();
//		List<Videos> filteredVideos=new ArrayList<Videos>();
//		for(Videos video:allvideo) {
//			if(!video.getHref().equals(href)) {
//				filteredVideos.add(video);
//			}
//		}
//		return filteredVideos;
//	}
	public List<Videos> findAllVideosExcept(String href) {
	    List<Videos> allVideos = videoDao.findAll();
	    return allVideos.stream()
	                    .filter(video -> video.getHref() != null && !video.getHref().equals(href))
	                    .collect(Collectors.toList());
	}
}
