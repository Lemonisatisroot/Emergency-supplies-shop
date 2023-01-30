package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.CategoryMapper;
import com.apple.emergency.dao.pojo.Category;
import com.apple.emergency.dao.pojo.Commodity;
import com.apple.emergency.service.CategoryService;
import com.apple.emergency.service.CommodityService;
import com.apple.emergency.vo.CategoryVo;
import com.apple.emergency.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HASEE
 * @title CategoryServiceImpl
 * @date 2022/8/25 14:44
 * @description TODO
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CommodityService commodityService;

    @Override
    public Category findCategoryById(String categoryId) {
        return categoryMapper.selectById(categoryId);
    }

    @Override
    public Result getCategories() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getLevel,0);

        List<Category> categoryList = categoryMapper.selectList(queryWrapper);
        return Result.success(copyList(categoryList));
    }


    private List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for(Category category : categoryList){
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }

    private CategoryVo copy(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        List<CategoryVo> children = findCategoriesByParentId(category.getId(),category.getLevel());
        categoryVo.setChildren(children);
        return categoryVo;
    }

    private List<CategoryVo> findCategoriesByParentId(Long id, Integer level){
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId,id);
        wrapper.eq(Category::getLevel,level + 1);
        //wrapper.or().eq(Category::getLevel,2);
        List<Category> categories = categoryMapper.selectList(wrapper);
        return copyList(categories);
    }
}
