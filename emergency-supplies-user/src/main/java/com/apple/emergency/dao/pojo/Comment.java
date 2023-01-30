package com.apple.emergency.dao.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author HASEE
 * @title Comment
 * @date 2022/8/25 15:40
 * @description TODO
 */
@Data
public class Comment {

    @Id
    private Long id;

    private String content;

    private Long parentId;

    private Long toUid;

    private Integer level;

    private String createDate;

    private Long userId;

    private Long commodityId;
}
