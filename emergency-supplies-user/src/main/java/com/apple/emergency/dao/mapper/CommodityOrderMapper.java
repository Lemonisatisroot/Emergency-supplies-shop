package com.apple.emergency.dao.mapper;

import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.dao.pojo.CommodityOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author HASEE
 * @title CommodityOrderMapper
 * @date 2022/9/16 9:48
 * @description TODO
 */
public interface CommodityOrderMapper extends BaseMapper<CommodityOrder> {

    /**
     * 根据订单id查询商品信息
     * @param orderId
     * @return
     */
    List<Commodity> findDetailByOrderId(Long orderId);
}
