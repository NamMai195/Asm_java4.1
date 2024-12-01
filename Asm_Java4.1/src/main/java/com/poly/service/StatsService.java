package com.poly.service;

import java.util.List;

import com.poly.dto.VideoLikedInfo;

public interface StatsService {
	List<VideoLikedInfo> findvideoLikedInfo();
	
	Integer getTotalVideos();
	Integer getToTalUsers();
	Integer getToTalViews();
	Integer getToTalShare();
}
