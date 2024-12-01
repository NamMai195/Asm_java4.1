package com.poly.dao;

import java.util.List;

import com.poly.entity.Share;
import com.poly.entity.Videos;

public interface ShareDao {
	List<Share> findAll();

	Share findById(String id); // Phương thức này phải trả về Video và nhận một String là id

	Share create(Share entity);

	Share update(Share entity);

	Share delete(Share href);
}
