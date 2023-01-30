package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.LoginParams;

/**
 * @author HASEE
 * @title LoginService
 * @date 2022/8/23 15:43
 * @description TODO
 */
public interface LoginService {

    /**
     * 用户登录模块
     * @param loginParams
     * @return
     */
    Result login(LoginParams loginParams);

    /**
     * 用户注册模块
     * @param loginParams
     * @return
     */
    Result register(LoginParams loginParams);

    User checkToken(String token);

    Result logout(String token);
}
