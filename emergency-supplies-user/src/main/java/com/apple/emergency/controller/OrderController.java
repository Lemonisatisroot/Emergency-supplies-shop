package com.apple.emergency.controller;

import com.apple.emergency.service.OrderService;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.OrderParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HASEE
 * @title OrderController
 * @date 2022/8/26 9:24
 * @description TODO
 */
@RestController
//@RequestMapping("orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/client/orders")
    public Result orderList(@RequestBody OrderParam orderParam){
        return orderService.findMyOrder(orderParam);
    }

    @PostMapping("/client/orders/create")
    public Result createOrder(@RequestBody OrderParam orderParam){
        log.info("订单参数为------>" + orderParam);
        return orderService.createOrder(orderParam);
    }
}
