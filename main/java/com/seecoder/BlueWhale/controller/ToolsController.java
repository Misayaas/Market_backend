package com.seecoder.BlueWhale.controller;

import com.seecoder.BlueWhale.po.Store;
import com.seecoder.BlueWhale.service.ImageService;
import com.seecoder.BlueWhale.service.StoreService;
import com.seecoder.BlueWhale.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToolsController {

    private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);
    @Autowired
    ImageService imageService;

    @Autowired
    StoreService storeService;

    @PostMapping("/images")
    public ResultVO<String> upload(@RequestParam MultipartFile file){
        logger.info("Image upload started");
        return ResultVO.buildSuccess(imageService.upload(file));
    }

    @DeleteMapping("/images")
    public ResultVO<String> delete(@RequestParam String url){
        logger.info("Image delete started");
        return ResultVO.buildSuccess(imageService.delete(url));
    }
}
