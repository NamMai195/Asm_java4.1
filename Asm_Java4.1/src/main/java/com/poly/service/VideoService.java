package com.poly.service;

import java.util.List;
import com.poly.entity.Videos;

public interface VideoService {
    Videos findById(String id);  // Phương thức này phải trả về Video và nhận một String là id

    Videos findByHref(String href);

    List<Videos> findAll();

    List<Videos> findAll(int pageNumber, int pageSize);
    
    List<Videos> findAllVideosExcept(String href);

    Videos create(Videos entity);

    Videos update(Videos entity);

    Videos delete(String  href);
}
