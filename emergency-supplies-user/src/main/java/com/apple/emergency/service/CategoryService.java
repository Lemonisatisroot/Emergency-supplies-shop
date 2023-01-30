package com.apple.emergency.service;

import com.apple.emergency.dao.pojo.Category;
import com.apple.emergency.vo.Result;

/**
 * @author HASEE
 * @title CategoryService
 * @date 2022/8/25 9:39
 * @description TODO
 */
public interface CategoryService {

    /**
     * 根据id查找分类
     * @param categoryId
     */
    Category findCategoryById(String categoryId);

    /**
     * 查询分类列表
     * @return
     */
    Result getCategories();

}
