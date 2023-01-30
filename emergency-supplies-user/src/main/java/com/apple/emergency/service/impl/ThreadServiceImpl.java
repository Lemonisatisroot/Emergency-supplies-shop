package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.CommodityMapper;
import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.service.ThreadService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author HASEE
 * @title ThreadServiceImpl
 * @date 2022/8/25 15:21
 * @description TODO
 */
@Service
@Component
public class ThreadServiceImpl implements ThreadService {

    @Async("taskExecutor")
    @Override
    public void updateViewCounts(CommodityMapper commodityMapper, Commodity commodity) {
        Commodity updateCommodity = new Commodity();
        updateCommodity.setViewCounts(commodity.getViewCounts() + 1);
        LambdaQueryWrapper<Commodity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Commodity::getId,commodity.getId());
        queryWrapper.eq(Commodity::getViewCounts,commodity.getViewCounts());
        commodityMapper.update(updateCommodity,queryWrapper);
    }

    @Async("taskExecutor")
    @Override
    public void updateCommentCounts(CommodityMapper commodityMapper, Commodity commodity) {
        Commodity updateCommodity = new Commodity();
        updateCommodity.setCommentCounts(commodity.getCommentCounts() + 1);
        LambdaQueryWrapper<Commodity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Commodity::getId,commodity.getId());
        queryWrapper.eq(Commodity::getCommentCounts,commodity.getCommentCounts());
        commodityMapper.update(updateCommodity,queryWrapper);
    }
}
