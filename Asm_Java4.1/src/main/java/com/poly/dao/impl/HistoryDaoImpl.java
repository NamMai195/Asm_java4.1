package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.HistoryDao;
import com.poly.entity.History;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

    @Override
    public List<History> findByUser(String username) {
        // SQL query tìm các History của người dùng theo username
        String sql = "SELECT h FROM History h WHERE h.user.id = ?0 AND h.video.isActive =1"
        		+"ORDER BY h.viewDate DESC";
        return findMany(History.class, sql, username);
    }

    @Override
    public List<History> findByUserAndIsliked(String username) {
        System.out.println("Username: " + username); // Kiểm tra giá trị username
        String sql = "SELECT h FROM History h WHERE h.user.id = ?0 "
                     + "AND h.isliked = 1 "
                     + "AND h.video.isActive = 1 "
                     + "ORDER BY h.viewDate DESC";
        return findMany(History.class, sql, username);
    }



    @Override
    public History findByUserIdAndVideoId(String userId, String videoId) {
        // SQL query tìm History dựa trên userId và videoId
        String sql = "SELECT h FROM History h WHERE h.user.id = ?0 AND h.video.videoId = ?1"+
        " AND h.video.isActive =1";
        return findOne(History.class, sql, userId, videoId);
    }

    @Override
    public History create(History entity) {
        // Tạo mới một History
        return super.create(entity);
    }

    @Override
    public History update(History entity) {
        // Cập nhật một History
        return super.update(entity);
    }

}
