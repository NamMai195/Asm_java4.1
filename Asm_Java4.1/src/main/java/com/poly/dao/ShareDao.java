package com.poly.dao;

import java.util.List;

import com.poly.entity.Share;

public interface ShareDao {
    List<Share> findAll();
}
