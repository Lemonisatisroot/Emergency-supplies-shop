package com.apple.emergency.controller;

import com.apple.emergency.service.CategoryService;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HASEE
 * @title CategoryController
 * @date 2022/9/7 9:27
 * @description TODO
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result getCategories(){
        return categoryService.getCategories();
    }
}
