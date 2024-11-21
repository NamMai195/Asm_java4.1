package com.poly.service.impl;

import java.util.List;

import com.poly.dao.StatsDao;
import com.poly.dao.impl.StatsDaoImlp;
import com.poly.dto.VideoLikedInfo;
import com.poly.service.StatsService;

public class StatsServiceImpl implements StatsService {
	private StatsDao statsdao;

	public StatsServiceImpl() {
		statsdao = new StatsDaoImlp();
	}

	@Override
	public List<VideoLikedInfo> findvideoLikedInfo() {
		// TODO Auto-generated method stub
		return statsdao.findVideoLikedInfo();
	}

}
