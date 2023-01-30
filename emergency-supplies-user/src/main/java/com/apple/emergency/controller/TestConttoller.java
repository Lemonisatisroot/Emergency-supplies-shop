package com.apple.emergency.controller;

import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.utils.UserThreadLocal;
import com.apple.emergency.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HASEE
 * @title TestConttoller
 * @date 2022/8/25 17:10
 * @description TODO
 */
@RestController
@RequestMapping("test")
public class TestConttoller {

    @GetMapping
    public Result test(){
        User user = UserThreadLocal.get();
        System.out.println(user);
        return Result.success(null);
    }
}
