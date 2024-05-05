package com.seecoder.BlueWhale.po;

import com.seecoder.BlueWhale.enums.OrderEnum;
import com.seecoder.BlueWhale.vo.OrderVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "store_id")
    private Integer storeId;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderEnum.OrderStatus orderStatus;
    @Basic
    @Column(name = "product_id")
    private Integer productId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "per_price")
    private Integer perPrice;
    @Basic
    @Column(name = "total_price")
    private Double totalPrice;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "pick_up_method")
    private OrderEnum.PickUpMethod pickUpMethod;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "create_time")
    private Date createTime;
    @Basic
    @Column(name = "update_time")
    private Date updateTime;
    @Basic
    @Column(name = "coupon")
    private Integer couponId;
    @Basic
    @Column(name = "paymethod")
    private String payMethod;

    public OrderVO toVO(){
        OrderVO orderVO = new OrderVO();
        orderVO.setId(this.id);
        orderVO.setUserId(this.userId);
        orderVO.setStoreId(this.storeId);
        orderVO.setOrderStatus(this.orderStatus);
        orderVO.setProductId(this.productId);
        orderVO.setQuantity(this.quantity);
        orderVO.setPerPrice(this.perPrice);
        orderVO.setTotalPrice(this.totalPrice);
        orderVO.setPickUpMethod(this.pickUpMethod);
        orderVO.setComment(this.comment);
        orderVO.setCouponId(this.couponId);
        orderVO.setPayMethod(this.payMethod);
        orderVO.setCreateTime(this.createTime);
        orderVO.setUpdateTime(this.updateTime);

        return orderVO;
    }
}
