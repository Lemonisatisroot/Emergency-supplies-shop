package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Comment;
import com.apple.emergency.vo.CommentVo;

import java.util.List;

/**
 * @author HASEE
 * @title MongodbService
 * @date 2022/9/13 11:39
 * @description TODO
 */
public interface MongodbService {
    /**
     * 保存用户评论
     * @param comment
     * @return
     */
    String saveObj(Comment comment);

    /**
     * 根查询评论列表
     * @return
     */
    List<CommentVo> findAll();

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    CommentVo getCommentById(Long id);

    /**
     * 根据父id查询子评论
     * @param id
     * @return
     */
    List<CommentVo> getChildrenByParentId(Long id);

    /**
     * 更新评论
     * @param comment
     * @return
     */
    String updateComment(Comment comment);

    /**
     *
     * @param commodityId
     * @return
     */
    List<CommentVo> findCommentByCommodityId(Long commodityId);
}
