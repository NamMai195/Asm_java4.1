package com.poly.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.HistoryDao;
import com.poly.dao.ShareDao;
import com.poly.dao.StatsDao;
import com.poly.dao.UserDao;
import com.poly.dao.VideoDao;
import com.poly.dto.VideoLikedInfo;
import com.poly.entity.History;

public class StatsDaoImlp extends AbstractDao<Object[]> implements StatsDao {
	private VideoDao videoDao = new VideoDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private HistoryDao hisdao = new HistoryDaoImpl();
	private ShareDao sharedao = new ShareDaoImpl();

	public List<VideoLikedInfo> findVideoLikedInfo() {
	    String sql = "SELECT " +
	                 "  v.video_id, " +
	                 "  v.title, " +
	                 "  v.href, " +
	                 "  v.views, " +
	                 "  SUM(CAST(h.isLike AS INT)) AS totallike, " +
	                 "  COUNT(s.share_id) AS totalshare " + // Thêm tổng số lượt chia sẻ
	                 "FROM Videos v " +
	                 "LEFT JOIN History h " +
	                 "  ON v.video_id = h.video_id " +
	                 "LEFT JOIN Shares s " + // Join với bảng Shares
	                 "  ON v.video_id = s.video_id " +
	                 "WHERE v.isActive = 1 " +
	                 "GROUP BY " +
	                 "  v.video_id, " +
	                 "  v.title, " +
	                 "  v.href, " +
	                 "  v.views " +
	                 "ORDER BY " +
	                 "  SUM(CAST(h.isLike AS INT)) DESC";

	    List<Object[]> objects = findManyByNativeQuery(sql);
	    List<VideoLikedInfo> result = new ArrayList<>();
	    objects.forEach(object -> {
	        VideoLikedInfo likedInfo = new VideoLikedInfo();
	        likedInfo.setVideoId((String) object[0]);
	        likedInfo.setTitle((String) object[1]);
	        likedInfo.setHref((String) object[2]);
	        likedInfo.setViews((Integer) object[3]);
	        likedInfo.setTotalLike(object[4] == null ? 0 : ((Number) object[4]).intValue());
	        likedInfo.setTotalShare(object[5] == null ? 0 : ((Number) object[5]).intValue()); // Thêm tổng số lượt chia sẻ vào likedInfo
	        result.add(likedInfo);
	    });
	    return result;
	}

	public Integer getTotalVideos() {
		// TODO Auto-generated method stub
		return videoDao.findAll().size();
	}

	public Integer getToTalUsers() {
		// TODO Auto-generated method stub
		return userDao.findAll().size();
	}

	public Integer getToTalViews() {
		String jpql = "SELECT SUM(v.views) FROM Videos v";
		Object result = super.findOneByJpql(jpql);
		if (result instanceof Long) {
			return ((Long) result).intValue(); // Ép kiểu từ Long sang Integer
		} else if (result == null) {
			return 0; // Hoặc giá trị mặc định khác nếu result là null
		} else {
			// Xử lý lỗi hoặc trả về giá trị mặc định nếu result không phải Long
			return 0;
		}
	}

	@Override
	public Integer getToTalShare() {
		// TODO Auto-generated method stub
		return sharedao.findAll().size();
	}

}
