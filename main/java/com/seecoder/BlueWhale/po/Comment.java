package com.seecoder.BlueWhale.po;
import com.seecoder.BlueWhale.vo.CommentVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "product_id")
    private Integer productId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "order_id")
    private Integer orderId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "time")
    private Date time;
    @Basic
    @Column(name = "score")
    private Float score;
    @Basic
    @Column(name = "user_avatar_url")
    private String userAvatarUrl;

    public CommentVO toVO() {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(this.id);
        commentVO.setUserId(this.userId);
        commentVO.setProductId(this.productId);
        commentVO.setOrderId(this.orderId);
        commentVO.setContent(this.content);
        commentVO.setTime(this.time);
        commentVO.setUserName(this.userName);
        commentVO.setUserAvatarUrl(this.userAvatarUrl);
        commentVO.setScore(this.score);
        return commentVO;
    }
}
