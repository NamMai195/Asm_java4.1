package com.lab3.dao;

import java.util.List;
import com.lab3.entity.Share;

public interface ShareDao {
    Share findById(String id);

    List<Share> findByUserId(String userId);

    List<Share> findByVideoId(String videoId);

    List<Share> findByEmails(String emails);

    List<Share> findAll();

    List<Share> findAll(int pageNumber, int pageSize);

    Share create(Share entity);

    Share update(Share entity);

    Share delete(Share entity);
}
