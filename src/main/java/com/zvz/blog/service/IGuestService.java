package com.zvz.blog.service;


import com.zvz.blog.model.Guest;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘俊 on 2016/11/2.
 */
public interface IGuestService {

    /**
     *
     *查询所有游客信息
     */
    List<Guest> getAllGuests();

    /**
     * @param condition
     * 根据条件查询游客信息
     */
    Guest queryOneByCondition(Map<String, Object> condition);

    /**
     * 新增
     */
    int addGuest(Guest guest);

    /**
     * 修改
     */
    int updateGuest(Guest guest);


}
