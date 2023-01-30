package com.apple.emergency.controller;

import com.apple.emergency.service.LoginService;
import com.apple.emergency.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;

/**
 * @author HASEE
 * @title LogoutController
 * @date 2022/8/23 16:56
 * @description TODO
 */
@RestController
//@RequestMapping("logout")
public class LogoutController {

    private static final String PAYMENT_URL="http://localhost:8889";

//    @Autowired
//    private LoginService loginService;
//
//    @GetMapping
//    public Result logout(@RequestHeader("Authorization") String token){
//        return loginService.logout(token);
//    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/client/logout")
    public Result logout(@RequestHeader("Authorization") String token){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization", token);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        return restTemplate.postForObject(PAYMENT_URL + "/logout" , requestEntity ,Result.class);
    }
}
