package com.apple.emergency.vo;

import com.apple.emergency.dao.pojo.Category;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @author HASEE
 * @title CategoryVo
 * @date 2022/9/7 9:19
 * @description TODO
 */
@Data
public class CategoryVo {

    private Long id;

    private String categoryName;

    private String description;

    private List<CategoryVo> children;
}
