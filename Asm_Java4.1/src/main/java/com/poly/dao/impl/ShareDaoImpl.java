package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.ShareDao;
import com.poly.entity.Share;

public class ShareDaoImpl extends AbstractDao<Share> implements ShareDao {

    @Override
    public List<Share> findAll() {
        // Tìm tất cả các Share có trạng thái isActive = true
        return super.findAll(Share.class, true);
    }
}
