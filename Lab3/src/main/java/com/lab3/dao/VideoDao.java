package com.lab3.dao;

import java.util.List;
import com.lab3.entity.Video;

public interface VideoDao {
    Video findById(String id);

    List<Video> findByTitle(String title);

    List<Video> findAll();

    List<Video> findAll(int pageNumber, int pageSize);

    Video create(Video entity);

    Video update(Video entity);

    Video delete(Video entity);
}
