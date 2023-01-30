package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.PictureMapper;
import com.apple.emergency.dao.pojo.Picture;
import com.apple.emergency.service.PictureService;
import com.apple.emergency.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HASEE
 * @title PictureServiceImpl
 * @date 2022/9/8 23:19
 * @description TODO
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> findPicturesByCommodityId(Long id) {

        return pictureMapper.findPicturesByCommodityId(id);
    }

    @Override
    public Result findPictureList() {
        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.last("limit 4");
        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
        return Result.success(pictureList);
    }
}
