package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.dao.pojo.CommodityOrder;

import java.util.List;

/**
 * @author HASEE
 * @title CommodityOrderService
 * @date 2022/9/16 9:48
 * @description TODO
 */
public interface CommodityOrderService {
    /**
     * 保存订单商品信息
     * @param commodityOrder
     */
    void saveCommodityOrder(CommodityOrder commodityOrder);

    /**
     * 根据订单id查询商品信息列表
     * @param id
     * @return
     */
    List<Commodity> findDetailByOrderId(Long id);
}
