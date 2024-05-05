package com.seecoder.BlueWhale.serviceImpl;


import com.seecoder.BlueWhale.po.Comment;
import com.seecoder.BlueWhale.po.Order;
import com.seecoder.BlueWhale.po.Product;
import com.seecoder.BlueWhale.repository.OrderRepository;
import com.seecoder.BlueWhale.repository.ProductRepository;
import com.seecoder.BlueWhale.service.CommentService;
import com.seecoder.BlueWhale.repository.CommentRepository;
import com.seecoder.BlueWhale.service.UserService;
import com.seecoder.BlueWhale.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository ProductRepository;
    @Autowired
    CommentRepository CommentRepository;

    public Boolean createComment(CommentVO commentVO){

        Comment newComment = commentVO.toPO();
        newComment.setUserId(userService.getInformation().getId());
        newComment.setUserName(userService.getInformation().getName());
        newComment.setTime(new Date());
        newComment.setScore(commentVO.getScore());
        Order order = orderRepository.findById(newComment.getOrderId()).get();
        order.setComment(newComment.getContent());
        orderRepository.save(order);
        Product product = ProductRepository.findById(newComment.getProductId()).get();
        try{
            commentRepository.save(newComment);
        } catch(Exception e){
            return false;
        }
        product.setScore(CommentRepository.findTotalScoreByProductId(newComment.getProductId())/CommentRepository.countByProductId(newComment.getProductId()));
        ProductRepository.save(product);
        return true;
    }

    public List<CommentVO> getComments(Integer productId){
        List<Comment> comments = commentRepository.findAllByProductId(productId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for(Comment comment: comments){
            commentVOS.add(comment.toVO());
        }
        return commentVOS;
    }



}

