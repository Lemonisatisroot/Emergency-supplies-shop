package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.OrderMapper;
import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.dao.pojo.CommodityOrder;
import com.apple.emergency.dao.pojo.Order;
import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.service.CommodityOrderService;
import com.apple.emergency.service.CommodityService;
import com.apple.emergency.service.OrderService;
import com.apple.emergency.service.UserService;
import com.apple.emergency.utils.UserThreadLocal;
import com.apple.emergency.vo.OrderVo;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.OrderParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author HASEE
 * @title OrderServiceImpl
 * @date 2022/8/26 9:29
 * @description TODO
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private CommodityOrderService commodityOrderService;

    @Override
    public Result findMyOrder(OrderParam orderParam) {
        User user = UserThreadLocal.get();
        log.info("登录用户为 ------>" + user);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId,user.getId());
        queryWrapper.orderByDesc(Order::getOrderStatus);
        List<Order> orderList = orderMapper.selectList(queryWrapper);
        return Result.success(copyList(orderList));
    }

    @Override
    public Result createOrder(OrderParam orderParam) {
        Order order = new Order();
        //SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        //String orderId = format.format(System.currentTimeMillis());
        //order.setOrderId(orderId);
        User user = UserThreadLocal.get();
        log.info("正在登录的用户是------->" + user);
        order.setUserId(user.getId());
        log.info("登录用户的id为------>" + user.getId());
        order.setOrderPrice(orderParam.getOrderPrice());
        order.setOrderStatus(orderParam.getOrderStatus());
        //order.setUserId(orderParam.getUserId());
        order.setCreateDate(new DateTime(System.currentTimeMillis()).toString("yyyy-MM-dd HH:mm:ss"));
        this.orderMapper.insert(order);
        for(Commodity commodity : orderParam.getGoods()){
            CommodityOrder commodityOrder = new CommodityOrder();
            commodityOrder.setGoodsId(commodity.getId());
            commodityOrder.setOrderId(order.getId());
            //commodityOrder.setGoodsNumber();
            commodityOrderService.saveCommodityOrder(commodityOrder);
        }
        log.info("生成的订单号为---->" + order.getId());
        return Result.success(order.getId());
    }

    private List<OrderVo> copyList(List<Order> orderList) {
        List<OrderVo> orderVos = new ArrayList<>();
        for (Order order : orderList){
            orderVos.add(copy(order));
        }
        return orderVos;
    }

    private OrderVo copy(Order order){
        OrderVo orderVo = new OrderVo();
        User user = userService.getUser(order.getUserId());
        List<Commodity> orderDetail = commodityOrderService.findDetailByOrderId(order.getId());

        orderVo.setOrderId(order.getId());
        orderVo.setOrderStatus(order.getOrderStatus());
        orderVo.setUserName(user.getName());
        orderVo.setPrice(order.getOrderPrice());
//        List<String> commodityNameList = new ArrayList<>();
//        for (Commodity commodity : orderDetail){
//            commodityNameList.add(commodity.getCommodityName());
//        }
//        orderVo.setCommodityName(commodityNameList);
        orderVo.setOrderDetail(orderDetail);


        return orderVo;
    }
}
