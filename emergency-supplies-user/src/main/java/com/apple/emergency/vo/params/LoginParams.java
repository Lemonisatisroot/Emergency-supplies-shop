package com.apple.emergency.vo.params;

import lombok.Data;

/**
 * @author HASEE
 * @title LoginParams
 * @date 2022/8/23 15:18
 * @description TODO
 */
@Data
public class LoginParams {

    private String name;

    private String password;

    private String nickname;

    private Integer sex;

    private String email;

    private Long phone;

    private String account;
}
