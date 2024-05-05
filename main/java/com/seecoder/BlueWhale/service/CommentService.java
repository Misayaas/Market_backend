package com.seecoder.BlueWhale.service;

import com.seecoder.BlueWhale.vo.CommentVO;

import java.util.List;

public interface CommentService {
    Boolean createComment(CommentVO commentVO);
    List<CommentVO> getComments(Integer productId);

}
