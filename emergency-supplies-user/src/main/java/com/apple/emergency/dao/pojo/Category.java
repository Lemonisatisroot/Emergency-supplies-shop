package com.apple.emergency.dao.pojo;

import lombok.Data;

/**
 * @author HASEE
 * @title Category
 * @date 2022/8/25 9:34
 * @description TODO
 */
@Data
public class Category {

    private Long id;

    private Long parentId;

    private String description;

    private Integer level;

    private String categoryName;
}
