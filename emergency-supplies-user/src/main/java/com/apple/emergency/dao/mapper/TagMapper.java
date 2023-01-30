package com.apple.emergency.dao.mapper;

import com.apple.emergency.dao.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author HASEE
 * @title TagMapper
 * @date 2022/8/25 9:40
 * @description TODO
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据商品的id查询标签
     * @param id
     * @return
     */
    List<Tag> findTagsByCommodityId(Long id);
}
