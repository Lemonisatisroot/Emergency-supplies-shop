package com.apple.emergency.vo;

import com.apple.emergency.dao.pojo.Category;
import com.apple.emergency.dao.pojo.Picture;
import com.apple.emergency.dao.pojo.Tag;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author HASEE
 * @title CommodityVo
 * @date 2022/8/25 9:36
 * @description TODO
 */

@Data
public class CommodityVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String commodityName;

    private String image;

    private String introduce;

    private List<Tag> tags;

    private Category category;

    private Integer viewCounts;

    private Integer commentCounts;

    private BigDecimal price;

    private String content;

    private List<Picture> pictures;
}
