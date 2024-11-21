package com.lab3.service;

import com.lab3.entity.Favorite;
import com.lab3.entity.User;
import com.lab3.entity.Video;

import java.util.List;

public interface FavoriteService {
    
    // Tìm kiếm video yêu thích theo tên người dùng, email hoặc ID
    List<Favorite> searchFavorites(String searchQuery);

    // Thêm video vào danh sách yêu thích
    void addFavorite(User user, Video video);

    // Xóa video khỏi danh sách yêu thích
    void removeFavorite(String userId, String videoId);

    // Lấy tất cả video yêu thích của một người dùng
    List<Favorite> getFavoritesByUserId(String userId);

    // Các phương thức khác có thể thêm vào sau
    List<Favorite> findall();
    	
    
}

