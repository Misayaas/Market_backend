package com.seecoder.BlueWhale.controller;


import com.seecoder.BlueWhale.service.CommentService;
import com.seecoder.BlueWhale.vo.CommentVO;
import com.seecoder.BlueWhale.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public ResultVO<Boolean> createComment(@RequestBody CommentVO commentVO){
        return ResultVO.buildSuccess(commentService.createComment(commentVO));
    }

    @GetMapping("/{productId}/get")
    public ResultVO getComments(@PathVariable Integer productId){
        return ResultVO.buildSuccess(commentService.getComments(productId));
    }
}
