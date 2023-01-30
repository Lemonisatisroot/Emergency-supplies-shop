package com.apple.emergency.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author HASEE
 * @title LoginUserVo
 * @date 2022/8/23 16:44
 * @description TODO
 */
@Data
public class LoginUserVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String account;

    private String nickname;

    private String avatar;

    private String email;

    private Long phone;


}
