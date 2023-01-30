package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.CommodityBodyMapper;
import com.apple.emergency.dao.pojo.CommodityBody;
import com.apple.emergency.service.CommodityBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HASEE
 * @title CommodityBodyServiceImpl
 * @date 2022/9/8 9:02
 * @description TODO
 */
@Service
public class CommodityBodyServiceImpl implements CommodityBodyService {

    @Autowired
    private CommodityBodyMapper commodityBodyMapper;


    @Override
    public CommodityBody findCommodityBodyById(Long id) {
        return commodityBodyMapper.selectById(id);
    }
}
