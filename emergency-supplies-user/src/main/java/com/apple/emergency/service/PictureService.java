package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Picture;
import com.apple.emergency.vo.Result;

import java.util.List;

/**
 * @author HASEE
 * @title PictureService
 * @date 2022/9/8 23:19
 * @description TODO
 */
public interface PictureService {

    /**
     * 根据商品id查询图片列表
     * @param id
     * @return
     */
    List<Picture> findPicturesByCommodityId(Long id);

    /**
     * 返回图片列表
     * @return
     */
    Result findPictureList();
}
