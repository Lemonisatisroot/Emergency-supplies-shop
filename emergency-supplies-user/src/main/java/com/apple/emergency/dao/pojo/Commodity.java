package com.apple.emergency.dao.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author HASEE
 * @title Commodity
 * @date 2022/8/25 9:00
 * @description TODO
 */
@Data
public class Commodity {

    private Long id;

    private String commodityName;

    private String image;

    private String introduce;

    private String categoryId;

    private Integer viewCounts;

    private Integer CommentCounts;

    private BigDecimal price;

    private Long bodyId;
}
