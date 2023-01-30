package com.apple.emergency.service;

import com.apple.emergency.vo.Result;

/**
 * @author HASEE
 * @title CommentService
 * @date 2022/8/25 15:51
 * @description TODO
 */
public interface CommentService {
    /**
     * 根据商品id查询评论
     * @param id
     * @return
     */
    Result commentsByCommodityId(Long id);
}
