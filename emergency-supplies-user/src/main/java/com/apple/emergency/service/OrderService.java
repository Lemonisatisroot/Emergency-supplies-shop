package com.apple.emergency.service;

import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.OrderParam;

/**
 * @author HASEE
 * @title OrderService
 * @date 2022/8/26 9:29
 * @description TODO
 */
public interface OrderService {

    /**
     * 根据用户id查看订单
     * @param orderParam
     * @return
     */
    Result findMyOrder(OrderParam orderParam);

    /**
     * 新建用户账单
     * @param orderParam
     * @return
     */
    Result createOrder(OrderParam orderParam);
}
