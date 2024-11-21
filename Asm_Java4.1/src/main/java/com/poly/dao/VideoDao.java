package com.poly.dao;

import java.util.List;

import com.poly.entity.Videos;

public interface VideoDao {
	Videos findById(String id); // Phương thức này phải trả về Video và nhận một String là id

	Videos findByHref(String href);

	List<Videos> findAll();

	List<Videos> findAll(int pageNumber, int pageSize);

	Videos create(Videos entity);

	Videos update(Videos entity);

	Videos delete(Videos entity);
	
	

}
