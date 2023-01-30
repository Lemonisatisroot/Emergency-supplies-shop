package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Tag;

import java.util.List;

/**
 * @author HASEE
 * @title TagService
 * @date 2022/8/25 9:39
 * @description TODO
 */
public interface TagService {

    /**
     * 根据商品id查询标签信息
     * @param id
     * @return
     */
    List<Tag> findTagsByCommodityId(Long id);
}
