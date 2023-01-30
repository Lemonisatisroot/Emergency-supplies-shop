package com.apple.emergency.vo;

import com.apple.emergency.dao.pojo.Comment;
import com.apple.emergency.dao.pojo.Commodity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author HASEE
 * @title OrderVo
 * @date 2022/8/26 9:36
 * @description TODO
 */
@Data
public class OrderVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    private String userName;

//    private List<String> commodityName;
//
//    private List<String> image;

    private List<Commodity> orderDetail;

    private BigDecimal price;

    private Integer orderStatus;


}
