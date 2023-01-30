package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.dao.pojo.CommodityBody;

/**
 * @author HASEE
 * @title CommodityBodyService
 * @date 2022/9/8 9:02
 * @description TODO
 */
public interface CommodityBodyService {

    /**
     * 根据id查找商品详细信息
     * @param id
     * @return
     */
    CommodityBody findCommodityBodyById(Long id);
}
