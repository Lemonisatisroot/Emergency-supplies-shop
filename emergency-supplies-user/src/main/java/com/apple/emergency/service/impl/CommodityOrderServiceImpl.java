package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.CommodityOrderMapper;
import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.dao.pojo.CommodityOrder;
import com.apple.emergency.service.CommodityOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HASEE
 * @title CommodityOrderServiceImpl
 * @date 2022/9/16 9:49
 * @description TODO
 */
@Service
public class CommodityOrderServiceImpl implements CommodityOrderService {

    @Autowired
    private CommodityOrderMapper commodityOrderMapper;

    @Override
    public void saveCommodityOrder(CommodityOrder commodityOrder) {
        commodityOrderMapper.insert(commodityOrder);
    }

    @Override
    public List<Commodity> findDetailByOrderId(Long id) {
        List<Commodity> detail = commodityOrderMapper.findDetailByOrderId(id);
        return detail;
    }
}
