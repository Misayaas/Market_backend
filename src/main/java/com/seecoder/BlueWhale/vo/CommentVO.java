package com.seecoder.BlueWhale.vo;

import com.seecoder.BlueWhale.po.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class CommentVO {
    private int id;
    private int productId;
    private int userId;
    private String userName;
    private int orderId;
    private String content;
    private Date time;
    private Float score;
    private String userAvatarUrl;

    public Comment toPO(){
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setUserId(this.userId);
        comment.setProductId(this.productId);
        comment.setOrderId(this.orderId);
        comment.setContent(this.content);
        comment.setTime(this.time);
        comment.setUserName(this.userName);
        comment.setUserAvatarUrl(this.userAvatarUrl);
        comment.setScore(this.score);
        return comment;
    }
}
