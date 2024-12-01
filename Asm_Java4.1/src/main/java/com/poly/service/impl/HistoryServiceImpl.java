package com.poly.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.poly.dao.HistoryDao;
import com.poly.dao.impl.HistoryDaoImpl;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Videos;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;

public class HistoryServiceImpl implements HistoryService {
	private HistoryDao dao;

	public HistoryServiceImpl() {
		dao = new HistoryDaoImpl();
	}

	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsliked(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserAndIsliked(username);
	}

	@Override
	public History findByUserIdAndVideoId(String userId, String videoId) {
		// TODO Auto-generated method stub
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Videos video) {
		// TODO Auto-generated method stub
		
		History his = dao.findByUserIdAndVideoId(user.getId(), video.getVideoId());
		if(his==null) {
			his=new History();
			his.setUser(user);
			his.setVideo(video);
			his.setViewDate(new Timestamp(System.currentTimeMillis()));
			his.setIsliked(Boolean.FALSE);
			return dao.create(his);
		}
		else {
			return his;
		}
		
		
	}

	@Override
	public boolean updateLikeOrUnLike(User user, String videoHref) {
		// TODO Auto-generated method stub
		VideoService video = new VideoServiceImpl();
		Videos video1 = video.findByHref(videoHref);

		History his = dao.findByUserIdAndVideoId(user.getId(), video1.getVideoId());
		if(his.getIsliked()==Boolean.FALSE) {
			his.setIsliked(Boolean.TRUE);
			his.setLikeDate(new Timestamp(System.currentTimeMillis()));
		}
		else {
			his.setIsliked(Boolean.FALSE);
			his.setLikeDate(null);
		}
		History updateHistory=dao.update(his);
		return updateHistory != null ? true : false;
	}

	@Override
	public Integer findByVideoIdAndIsliked(String videoId) {
		// TODO Auto-generated method stub
		return dao.findByVideoIdAndIsliked(videoId);
	}

}
