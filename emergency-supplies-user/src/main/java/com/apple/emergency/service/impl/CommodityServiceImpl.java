package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.CommodityMapper;
import com.apple.emergency.dao.pojo.*;
import com.apple.emergency.service.*;
import com.apple.emergency.vo.CommodityVo;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.PageParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HASEE
 * @title CommodityServiceImpl
 * @date 2022/8/25 9:46
 * @description TODO
 */
@Service
@Slf4j
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private TagService tagService;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private CommodityBodyService commodityBodyService;

    @Autowired
    private PictureService pictureService;

    @Override
    public Result commodityList(PageParams pageParams) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        Page<Commodity> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        Page<Commodity> commodityPage = commodityMapper.selectPage(page, queryWrapper);
        List<CommodityVo> commodityVoList = copyList(commodityPage.getRecords(),true,false);
        return Result.success(commodityVoList);
    }

    @Override
    public Result findCommodityById(Long id) {
        Commodity commodity = commodityMapper.selectById(id);
        threadService.updateViewCounts(commodityMapper,commodity);
        CommodityBody body = commodityBodyService.findCommodityBodyById(commodity.getBodyId());
        List<Picture> pictures = pictureService.findPicturesByCommodityId(commodity.getId());
        log.info("浏览次数已更新----->>>");
        CommodityVo commodityVo = copy(commodity, true, true);
        commodityVo.setContent(body.getContent());
        commodityVo.setPictures(pictures);
        return Result.success(commodityVo);
    }

    @Override
    public Commodity getCommodityById(Long id) {
        Commodity commodity = commodityMapper.selectById(id);
        return commodity;
    }

    @Override
    public Result commodityByCategoryId(PageParams pageParams) {
        LambdaQueryWrapper<Commodity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Commodity::getCategoryId,pageParams.getCategoryId());
        Page<Commodity> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        Page<Commodity> commodityPage = commodityMapper.selectPage(page, queryWrapper);
        List<CommodityVo> commodityVoList = copyList(commodityPage.getRecords(),true,false);
        return Result.success(commodityVoList);
    }

    @Override
    public Result searchMsg(String search) {
        LambdaQueryWrapper<Commodity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Commodity::getViewCounts);
        queryWrapper.select(Commodity::getId,Commodity::getCommodityName);
        queryWrapper.like(Commodity::getCommodityName,search);
        List<Commodity> commodityList = commodityMapper.selectList(queryWrapper);
        return Result.success(copyList(commodityList,false,false));
    }

    public CommodityVo copy(Commodity commodity,boolean isTags,boolean isCategory){
        CommodityVo commodityVo = new CommodityVo();
        BeanUtils.copyProperties(commodity, commodityVo);

        if (isTags){
            List<Tag> tags = tagService.findTagsByCommodityId(commodity.getId());
            commodityVo.setTags(tags);
        }
        if(isCategory){
            Category category = categoryService.findCategoryById(commodity.getCategoryId());
            commodityVo.setCategory(category);
        }
        return commodityVo;
    }

    private List<CommodityVo> copyList(List<Commodity> records,boolean isTags,boolean isCategory) {
        List<CommodityVo> commodityVoList = new ArrayList<>();
        for (Commodity commodity : records) {
            CommodityVo commodityVo = copy(commodity,isTags,isCategory);
            commodityVoList.add(commodityVo);
        }
        return commodityVoList;
    }
}
