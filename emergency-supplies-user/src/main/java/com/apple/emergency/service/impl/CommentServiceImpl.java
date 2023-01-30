package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.CommentMapper;
import com.apple.emergency.dao.pojo.Comment;
import com.apple.emergency.service.CommentService;
import com.apple.emergency.service.UserService;
import com.apple.emergency.vo.CommentVo;
import com.apple.emergency.vo.LoginUserVo;
import com.apple.emergency.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HASEE
 * @title CommentServiceImpl
 * @date 2022/8/25 15:53
 * @description TODO
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Override
    public Result commentsByCommodityId(Long id) {
        /**
         * 1.根据文章id 查询 评论列表
         * 2.根据作者id 查询 作者信息
         * 3.判断 如果level=1 查询是否有子评论
         * 4.如果有 根据评论id 查询 （parent_id）
         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getCommodityId,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);

        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();
        for(Comment comment : comments){
            commentVos.add(copy(comment));
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment){
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        //commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-HH-dd HH:mm"));
        commentVo.setCreateDate(comment.getCreateDate());
        //LoginUserVo loginUserVo = userService.findLoginUserVoById(comment.getUserId());

        //commentVo.setUser(loginUserVo);

        List<CommentVo> commentVoList = findCommentsByParentId(comment.getId());
        commentVo.setChildren(commentVoList);
        if (comment.getLevel() > 1) {
            Long toUid = comment.getToUid();
            LoginUserVo toUserVo = userService.findLoginUserVoById(toUid);
            commentVo.setToUser(toUserVo);

        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }
}
