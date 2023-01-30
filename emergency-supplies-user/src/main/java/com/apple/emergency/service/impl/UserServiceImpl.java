package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.UserMapper;
import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.service.UserService;
import com.apple.emergency.vo.ErrorCode;
import com.apple.emergency.vo.LoginUserVo;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.LoginParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HASEE
 * @title UserServiceImpl
 * @date 2022/8/23 15:08
 * @description TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(String nickname, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickname,nickname);
        queryWrapper.eq(User::getPassword,password);
        queryWrapper.select(User::getName,User::getId,User::getNickname,User::getEmail,User::getPhone);
        queryWrapper.last("limit 1");
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public void save(User newUser) {
        this.userMapper.insert(newUser);
    }

    @Override
    public Result getUserById(Long id) {
        User user = userMapper.selectById(id);
        return Result.success(user);
    }

    @Override
    public User getUser(Long id){
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public LoginUserVo findLoginUserVoById(Long userId) {
        User user = userMapper.selectById(userId);
        LoginUserVo loginUserVo = new LoginUserVo();
        if(user != null){
            BeanUtils.copyProperties(user,loginUserVo);
        }
        return loginUserVo;
    }


}
