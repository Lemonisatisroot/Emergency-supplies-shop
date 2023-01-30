package com.apple.emergency.dao.pojo;

import lombok.Data;

/**
 * @author HASEE
 * @title User
 * @date 2022/8/23 14:58
 * @description TODO
 */
@Data
public class User {

    private Long id;

    private String name;

    private String password;

    private String nickname;

    private String email;

    private Integer sex;

    private Long phone;
}
