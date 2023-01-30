package com.apple.emergency.controller;

import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.service.LoginService;
import com.apple.emergency.service.UserService;
import com.apple.emergency.utils.UserThreadLocal;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.LoginParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

/**
 * @author HASEE
 * @title LoginController
 * @date 2022/8/23 15:11
 * @description TODO
 */
@RestController
@Slf4j
//@RequestMapping("login")
public class LoginController {

    private static final String PAYMENT_URL="http://localhost:8889";

//    @Autowired
//    private LoginService loginService;
//
//    @PostMapping
//    public Result login(@RequestBody LoginParams loginParams){
//        return loginService.login(loginParams);
//    }
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/client/login")
    public Result login(LoginParams loginParams){
        return restTemplate.postForObject(PAYMENT_URL + "/login", loginParams ,Result.class);
    }

}
