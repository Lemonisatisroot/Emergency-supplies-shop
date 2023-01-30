package com.apple.emergency.vo.params;

import lombok.Data;

/**
 * @author HASEE
 * @title PageParams
 * @date 2022/8/25 10:36
 * @description TODO
 */
@Data
public class PageParams {

    private int page = 1;

    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;
}
