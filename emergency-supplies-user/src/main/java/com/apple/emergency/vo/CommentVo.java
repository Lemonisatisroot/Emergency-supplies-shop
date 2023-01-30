package com.apple.emergency.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author HASEE
 * @title CommentVo
 * @date 2022/8/25 15:47
 * @description TODO
 */
@Data
public class CommentVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String content;

    private LoginUserVo user;

    private Integer level;

    private String createDate;

    private List<CommentVo> children;

    private LoginUserVo toUser;
}
