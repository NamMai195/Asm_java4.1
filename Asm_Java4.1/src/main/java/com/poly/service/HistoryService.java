package com.poly.service;

import java.util.List;

import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Videos;

public interface HistoryService {
	List<History> findByUser(String username);

	List<History> findByUserAndIsliked(String username);

	History findByUserIdAndVideoId(String userId, String videoId);
	
	Integer findByVideoIdAndIsliked(String videoId);

	History create(User user,Videos video);

	boolean updateLikeOrUnLike(User user,String videoHref );
}
