package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.PageParams;

/**
 * @author HASEE
 * @title CommodityService
 * @date 2022/8/25 9:39
 * @description TODO
 */
public interface CommodityService {

    /**
     * 查询商品列表
     * @return
     */
    Result commodityList(PageParams pageParams);

    /**
     * 根据id查找对应的商品
     * @param id
     * @return
     */
    Result findCommodityById(Long id);

    Commodity getCommodityById(Long id);

    /**
     * 根据分类id查询商品列表
     * @param pageParams
     * @return
     */
    Result commodityByCategoryId(PageParams pageParams);

    /**
     * 根据字段查询信息
     * @param search
     * @return
     */
    Result searchMsg(String search);
}
