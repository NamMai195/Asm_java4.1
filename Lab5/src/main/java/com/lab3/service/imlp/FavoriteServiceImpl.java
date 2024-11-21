package com.lab3.service.imlp;

import java.util.List;

import com.lab3.dao.FavoriteDao;
import com.lab3.dao.impl.FavoriteDaoImpl;
import com.lab3.entity.Favorite;
import com.lab3.entity.User;
import com.lab3.entity.Video;
import com.lab3.service.FavoriteService;
import com.lab3.service.UserService;
public class FavoriteServiceImpl implements FavoriteService {
	
    private UserService userService = new UserserviceImpl(); // Bạn có thể inject userService từ bên ngoài hoặc DI
    private FavoriteDao dao = new FavoriteDaoImpl();  // Cách tạo đối tượng

    public FavoriteServiceImpl() {
        try {
            dao = new FavoriteDaoImpl(); // Khởi tạo trong constructor, nếu cần
        } catch (Exception e) {
            System.out.println("Error initializing FavoriteDaoImpl");
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Favorite> searchFavorites(String searchQuery) {
        User user = userService.findbytext(searchQuery); // Gọi tìm kiếm trong UserService
        if (user != null) {
            return dao.findByUserId(user.getUserId());
        }
        return null;
    }

    @Override
    public void addFavorite(User user, Video video) {
        // Thêm video yêu thích
    }

    @Override
    public void removeFavorite(String userId, String videoId) {
        // Xóa video khỏi danh sách yêu thích
    }

    @Override
    public List<Favorite> getFavoritesByUserId(String userId) {
        // Trả về danh sách các video yêu thích của người dùng theo userId
        return null;
    }
	@Override
	public List<Favorite> findall() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
}
