package com.apple.emergency.service;

import com.apple.emergency.dao.mapper.CommodityMapper;
import com.apple.emergency.dao.pojo.Commodity;

/**
 * @author HASEE
 * @title ThreadService
 * @date 2022/8/25 15:21
 * @description TODO
 */
public interface ThreadService {

    /**
     * 更新浏览次数
     */
    void updateViewCounts(CommodityMapper commodityMapper, Commodity commodity);

    /**
     * 更新评论次数
     */
    void updateCommentCounts(CommodityMapper commodityMapper, Commodity commodity);
}



