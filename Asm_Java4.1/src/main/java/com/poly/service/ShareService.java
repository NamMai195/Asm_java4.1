package com.poly.service;

import java.util.List;

import com.poly.entity.Share;
import com.poly.entity.Videos;

public interface ShareService {
	Share findById(String id); // Phương thức này phải trả về Video và nhận một String là id

	List<Share> findAll();

	Share create(Share entity);

	Share update(Share entity);

	Share delete(Share href);

}
