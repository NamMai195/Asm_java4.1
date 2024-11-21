package com.lab3.dao;

import java.util.List;
import com.lab3.entity.Favorite;

public interface FavoriteDao {
    Favorite findById(String id);

    List<Favorite> findByUserId(Integer userId);

    List<Favorite> findByVideoId(Integer videoId);

    List<Favorite> findAll();

    List<Favorite> findAll(int pageNumber, int pageSize);

    Favorite create(Favorite entity);

    Favorite update(Favorite entity);

    Favorite delete(Favorite entity);
}
