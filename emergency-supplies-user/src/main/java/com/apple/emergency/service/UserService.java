package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.vo.LoginUserVo;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.LoginParams;

/**
 * @author HASEE
 * @title UserService
 * @date 2022/8/23 15:08
 * @description TODO
 */
public interface UserService {

    /**
     * 根据昵称和密码查询用户
     * @param nickname
     * @param password
     */
    User findUser(String nickname, String password);

    /**
     * 保存新用户
     * @param newUser
     */
    void save(User newUser);

    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    Result getUserById(Long id);

    User getUser(Long id);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    LoginUserVo findLoginUserVoById(Long userId);
}
