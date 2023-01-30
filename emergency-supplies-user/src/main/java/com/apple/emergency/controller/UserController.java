package com.apple.emergency.controller;

import com.apple.emergency.service.UserService;
import com.apple.emergency.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HASEE
 * @title UserController
 * @date 2022/8/25 9:13
 * @description TODO
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") Long id){
        log.info("**************");
        return userService.getUserById(id);
    }
}
