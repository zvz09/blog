package com.zvz.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zvz.blog.dao.IGuestDao;
import com.zvz.blog.model.Guest;
import com.zvz.blog.service.IGuestService;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘俊 on 2016/11/2.
 */
@Service("guestService")
public class GuestServiceImpl implements IGuestService{

    @Autowired
    private IGuestDao guestDao;

    @Override
    public List<Guest> getAllGuests() {
        return guestDao.queryAll();
    }

    @Override
    public Guest queryOneByCondition(Map<String, Object> condition) {
        Guest g =  guestDao.queryOneByCondition(condition);
        return g;
    }

    @Override
    public int addGuest(Guest guest) {
        return guestDao.insert(guest);
    }

    @Override
    public int updateGuest(Guest guest) {
        return guestDao.updateByPrimaryKeySelective(guest);
    }

}
