package com.apple.emergency.vo.params;

import lombok.Data;

/**
 * @author HASEE
 * @title CommentParam
 * @date 2022/8/25 15:50
 * @description TODO
 */
@Data
public class CommentParam {

    private Long commodityId;

    private String content;

    private Long parent;

    private Long toUserId;
}
