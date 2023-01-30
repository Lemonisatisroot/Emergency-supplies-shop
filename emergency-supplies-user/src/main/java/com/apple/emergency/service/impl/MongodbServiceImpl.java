package com.apple.emergency.service.impl;

import com.apple.emergency.dao.pojo.Comment;
import com.apple.emergency.dao.pojo.User;
import com.apple.emergency.service.MongodbService;
import com.apple.emergency.service.UserService;
import com.apple.emergency.vo.CommentVo;
import com.apple.emergency.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author HASEE
 * @title MongodbServiceImpl
 * @date 2022/9/13 11:39
 * @description TODO
 */
@Service
@Slf4j
public class MongodbServiceImpl implements MongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Override
    public String saveObj(Comment comment) {
        log.info("MongoDB save -----------> start");
        comment.setCreateDate(new DateTime(System.currentTimeMillis()).toString("yyyy-MM-dd HH:mm"));
        mongoTemplate.save(comment);
        return "添加成功";
    }

    @Override
    public List<CommentVo> findAll() {
        log.info("MongoDB findAll -----------> start");
        //List<Comment> commentList = mongoTemplate.findAll(Comment.class);
        Query query = new Query();
        PageRequest pageRequest = PageRequest.of(0, 3);
        query.with(pageRequest);
        List<Comment> commentList = mongoTemplate.find(query, Comment.class);
        return copyList(commentList);
    }

    private List<CommentVo> copyList(List<Comment> commentList) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment){
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        User user = userService.getUser(comment.getUserId());
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user,loginUserVo);
        commentVo.setUser(loginUserVo);

        //子评论
        Integer level = comment.getLevel();
        if(1 == level){
            Long id = comment.getId();
            List<CommentVo> commentVos = getChildrenByParentId(id);
            commentVo.setChildren(commentVos);
        }
        //给谁评论
        if(level > 1){
            Long toUid = comment.getToUid();
            LoginUserVo toUserVo = this.userService.findLoginUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    @Override
    public CommentVo getCommentById(Long id) {
        return null;
    }

    @Override
    public List<CommentVo> getChildrenByParentId(Long id) {
        Query query = new Query(Criteria.where("parentId").is(id));
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        return copyList(comments);
    }

    @Override
    public String updateComment(Comment comment) {
        Query query = new Query(Criteria.where("_id").is(comment.getId()));
        Update update = new Update().set("content",comment.getContent())
                    .set("level",comment.getLevel());
        mongoTemplate.updateFirst(query,update,Comment.class);
        return "修改成功";
    }

    @Override
    public List<CommentVo> findCommentByCommodityId(Long commodityId) {
        Query query = new Query(Criteria.where("commodityId").is(commodityId).and("level").is(1));
        //PageRequest pageRequest = PageRequest.of(0, 3);
        //query.with(pageRequest);
        List<Comment> commentList = mongoTemplate.find(query, Comment.class);
        return copyList(commentList);
    }
}
