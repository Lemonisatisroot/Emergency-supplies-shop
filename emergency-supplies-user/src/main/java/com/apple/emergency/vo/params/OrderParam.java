package com.apple.emergency.vo.params;

import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.dao.pojo.CommodityOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author HASEE
 * @title OrderParam
 * @date 2022/8/26 9:23
 * @description TODO
 */
@Data
public class OrderParam {

    private Long id;

    private Integer orderStatus;

    private Long userId;

//    private List<Long> commodityId;
//
    private BigDecimal orderPrice;

    private List<Commodity> goods;
}
