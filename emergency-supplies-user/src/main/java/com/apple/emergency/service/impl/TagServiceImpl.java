package com.apple.emergency.service.impl;

import com.apple.emergency.dao.mapper.TagMapper;
import com.apple.emergency.dao.pojo.Tag;
import com.apple.emergency.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HASEE
 * @title TagServiceImpl
 * @date 2022/8/25 10:46
 * @description TODO
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findTagsByCommodityId(Long id) {
        return tagMapper.findTagsByCommodityId(id);
    }
}
