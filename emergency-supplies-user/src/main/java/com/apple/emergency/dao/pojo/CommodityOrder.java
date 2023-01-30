package com.apple.emergency.dao.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author HASEE
 * @title CommodityOrder
 * @date 2022/9/16 9:45
 * @description TODO
 */
@Data
public class CommodityOrder {

    private Long id;

    private Long orderId;

    private Long goodsId;

    private Long goodsNumber;
}
