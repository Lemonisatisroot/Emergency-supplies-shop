package com.apple.emergency.controller;

import com.apple.emergency.dao.pojo.CommodityBody;
import com.apple.emergency.service.CommodityService;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.CommodityParams;
import com.apple.emergency.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HASEE
 * @title CommodiryController
 * @date 2022/8/25 9:42
 * @description TODO
 */
@RestController
@RequestMapping("commodities")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @PostMapping
    public Result commodityList(@RequestBody PageParams pageParams){
        return commodityService.commodityList(pageParams);
    }

    @GetMapping("{goods_id}")
    public Result commodityById(@PathVariable("goods_id") Long id){
        return commodityService.findCommodityById(id);
    }

    @PostMapping("search")
    public Result commodityByCategoryId(@RequestBody PageParams pageParams){
        return commodityService.commodityByCategoryId(pageParams);
    }

    @PostMapping("qsearch")
    public Result searchMsg(@RequestBody CommodityParams commodityParams){
        String search = commodityParams.getSearch();
        return commodityService.searchMsg(search);
    }
}
