package com.apple.emergency.service.impl;

import com.alibaba.fastjson.JSON;
import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.service.LoginService;
import com.apple.emergency.service.UserService;
import com.apple.emergency.utils.JWTUtils;
import com.apple.emergency.vo.ErrorCode;
import com.apple.emergency.vo.LoginUserVo;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.LoginParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author HASEE
 * @title LoginServiceImpl
 * @date 2022/8/23 15:43
 * @description TODO
 */
@Service
@Slf4j
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Result login(LoginParams loginParams) {
        String password = loginParams.getPassword();
        String nickname = loginParams.getNickname();
        if(StringUtils.isBlank(nickname) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        User user = userService.findUser(nickname, password);
        if(user == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        log.info("用户信息为------->" + user.getName());


        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        log.info("token---->" + token);

        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(user.getId());
        loginUserVo.setAccount(user.getName());
        loginUserVo.setNickname(user.getNickname());
        loginUserVo.setPhone(user.getPhone());
        loginUserVo.setEmail(user.getEmail());
        loginUserVo.setAvatar("/static/img/touxiang.jpg");
        return Result.success(token);
    }

    @Override
    public Result register(LoginParams loginParams) {

        String password = loginParams.getPassword();
        String nickname = loginParams.getNickname();
        if(StringUtils.isBlank(nickname) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        User user = userService.findUser(nickname, password);
        if(user != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        User newUser = new User();
        newUser.setName(loginParams.getName());
        newUser.setNickname(nickname);
        newUser.setPassword(password);
        newUser.setPhone(loginParams.getPhone());
        newUser.setSex(loginParams.getSex());
        newUser.setEmail(loginParams.getEmail());

        this.userService.save(newUser);

        //创建token
        String token = JWTUtils.createToken(newUser.getId());
        //将信息保存到redis
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(newUser),1, TimeUnit.DAYS);
        return Result.success(null);
    }

    @Override
    public User checkToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }

        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if(stringObjectMap == null){
            return null;
        }

        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }

        User user = JSON.parseObject(userJson, User.class);

        return user;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }
}
