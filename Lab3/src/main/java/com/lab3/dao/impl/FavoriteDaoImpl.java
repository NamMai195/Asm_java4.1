package com.lab3.dao.impl;

import java.util.List;

import com.lab3.dao.AbstractDao;
import com.lab3.dao.FavoriteDao;
import com.lab3.entity.Favorite;

public class FavoriteDaoImpl extends AbstractDao<Favorite> implements FavoriteDao {

    @Override
    public Favorite findById(String id) {
        // Gọi phương thức findById từ lớp cha AbstractDao
        return findById(Favorite.class, id);  // Chú ý gọi đúng phương thức từ lớp cha
    }

    @Override
    public List<Favorite> findByUserId(Integer userId) {
        // Truy vấn để lấy các Favorite của người dùng theo userId
        String sql = "SELECT f FROM Favorite f WHERE f.user.userId = ?1";
        return findMany(Favorite.class, sql, userId);  // Gọi phương thức findMany từ lớp cha
    }

    @Override
    public List<Favorite> findByVideoId(Integer videoId) {
        // Truy vấn để lấy các Favorite của video theo videoId
        String sql = "SELECT f FROM Favorite f WHERE f.video.videoId = ?1";
        return findMany(Favorite.class, sql, videoId);  // Gọi phương thức findMany từ lớp cha
    }

    @Override
    public List<Favorite> findAll() {
        // Gọi phương thức findAll từ lớp cha
        return findAll(Favorite.class);  // Gọi phương thức findAll từ lớp cha
    }

    @Override
    public List<Favorite> findAll(int pageNumber, int pageSide) {
        // Gọi phương thức findAll với phân trang từ lớp cha
        return findAll(Favorite.class, pageNumber, pageSide);  // Gọi phương thức findAll với phân trang từ lớp cha
    }

    @Override
    public Favorite create(Favorite entity) {
        // Gọi phương thức create từ lớp cha để lưu Favorite vào cơ sở dữ liệu
        return super.create(entity);  // Gọi phương thức create từ lớp cha
    }

    @Override
    public Favorite update(Favorite entity) {
        // Gọi phương thức update từ lớp cha để cập nhật Favorite
        return super.update(entity);  // Gọi phương thức update từ lớp cha
    }

    @Override
    public Favorite delete(Favorite entity) {
        // Gọi phương thức delete từ lớp cha để xóa Favorite
        return super.delete(entity);  // Gọi phương thức delete từ lớp cha
    }
}
