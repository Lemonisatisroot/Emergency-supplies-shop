package com.apple.emergency.controller;

import com.apple.emergency.service.PictureService;
import com.apple.emergency.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HASEE
 * @title PictureController
 * @date 2022/9/9 21:59
 * @description TODO
 */
@RestController
@RequestMapping("pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping
    public Result pictureList() {
        return pictureService.findPictureList();
    }
}
