package com.apple.emergency.utils;

import com.apple.emergency.dao.pojo.User;

/**
 * @author HASEE
 * @title UserThreadLocal
 * @date 2022/8/25 16:51
 * @description TODO
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User user){
        LOCAL.set(user);
    }
    public static User get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
