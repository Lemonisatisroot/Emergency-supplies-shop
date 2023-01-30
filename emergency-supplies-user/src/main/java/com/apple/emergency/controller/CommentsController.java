package com.apple.emergency.controller;

import com.apple.emergency.dao.pojo.Comment;
import com.apple.emergency.service.CommentService;
import com.apple.emergency.service.MongodbService;
import com.apple.emergency.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HASEE
 * @title CommentsController
 * @date 2022/8/25 15:54
 * @description TODO
 */
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MongodbService mongodbService;

    @GetMapping("commodity/{goods_id}")
    public Result comments(@PathVariable("goods_id") Long id){
        return commentService.commentsByCommodityId(id);
    }

    @PostMapping("mongodb/save")
    public String saveObj(@RequestBody Comment comment) {
        return mongodbService.saveObj(comment);
    }

    @GetMapping("mongodb/findAllComment")
    public Result findAllComment(){
        return Result.success(mongodbService.findAll());
    }

    @GetMapping("mongodb/findAllComment/{goods_id}")
    public Result commentByCommodityId(@PathVariable("goods_id") Long id){
        return Result.success(mongodbService.findCommentByCommodityId(id));
    }
}
