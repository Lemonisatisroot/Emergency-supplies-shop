package com.apple.emergency.dao.mapper;

import com.apple.emergency.dao.pojo.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author HASEE
 * @title PictureMapper
 * @date 2022/9/8 23:19
 * @description TODO
 */
public interface PictureMapper extends BaseMapper<Picture> {

    /**
     * 根据商品id查询图片列表
     * @param id
     * @return
     */
    List<Picture> findPicturesByCommodityId(Long id);
}
