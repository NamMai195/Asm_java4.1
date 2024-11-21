package com.poly.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.StatsDao;
import com.poly.dto.VideoLikedInfo;

public class StatsDaoImlp extends AbstractDao<Object[]> implements StatsDao {

	public List<VideoLikedInfo> findVideoLikedInfo() {
		String sql = "select " + " v.video_id ,v.title, v.href,sum(cast(h.isLike as int)) as totallike"
				+ " from Videos v left join History h on v.video_id = h.video_id" + " where v.isActive = 1"
				+ " group by v.video_id ,v.title, v.href" + " order by sum(cast(h.isLike as int)) desc";
		List<Object[]> objects = findManyByNativeQuery(sql); // Sửa Object thành Object[]
		List<VideoLikedInfo> result = new ArrayList<>();
		objects.forEach(object -> {
			VideoLikedInfo likedInfo = new VideoLikedInfo();
			likedInfo.setVideoId((String) object[0]);
			likedInfo.setTitle((String) object[1]);
			likedInfo.setHref((String) object[2]);
			likedInfo.setTotalLike((Integer) object[3] == null ? 0 : (Integer) object[3]);
			result.add(likedInfo);
		});
		return result;
	}

}
