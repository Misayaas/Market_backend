package com.seecoder.BlueWhale.repository;

import com.seecoder.BlueWhale.po.Comment;
import com.seecoder.BlueWhale.po.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByProductId(Integer productId);
    @Query("SELECT SUM(c.score) FROM Comment c WHERE c.productId = :productId")
    Float findTotalScoreByProductId(@Param("productId") Integer productId);

    long countByProductId(Integer productId);
    List<Order> findAllByUserId(Integer userId);
}
