package com.apple.emergency.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author HASEE
 * @title Order
 * @date 2022/8/26 9:18
 * @description TODO
 */
@Data
public class Order {

    //@TableId(type= IdType.AUTO)
    private Long id;

    private Integer orderStatus;

    private BigDecimal orderPrice;

    private Long userId;

    //private Long commodityId;

    private String createDate;
}
