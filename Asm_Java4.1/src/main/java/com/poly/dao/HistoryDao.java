package com.poly.dao;

import java.util.List;

import com.poly.entity.History;

public interface HistoryDao {
	List<History> findByUser(String username);

	List<History> findByUserAndIsliked(String username);

	History findByUserIdAndVideoId(String userId, String videoId);

	History create(History entity);

	History update(History entity);

	Integer findByVideoIdAndIsliked(String videoId);
}
