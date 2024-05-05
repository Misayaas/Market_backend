package com.seecoder.BlueWhale.vo;

import com.seecoder.BlueWhale.enums.OrderEnum;
import com.seecoder.BlueWhale.po.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class OrderVO {
    private Integer id;
    private Integer userId;
    private Integer storeId;
    private OrderEnum.OrderStatus orderStatus;
    private Integer productId;
    private Integer quantity;
    private Integer perPrice;
    private Double totalPrice;
    private OrderEnum.PickUpMethod pickUpMethod;
    private String comment;
    private Integer couponId;
    private String payMethod;
    private Date createTime;
    private Date updateTime;

    public Order toPO(){
        Order order = new Order();
        order.setId(this.id);
        order.setUserId(this.userId);
        order.setStoreId(this.storeId);
        order.setOrderStatus(this.orderStatus);
        order.setProductId(this.productId);
        order.setQuantity(this.quantity);
        order.setPerPrice(this.perPrice);
        order.setTotalPrice(this.totalPrice);
        order.setPickUpMethod(this.pickUpMethod);
        order.setComment(this.comment);
        order.setCouponId(this.couponId);
        order.setPayMethod(this.payMethod);
        order.setCreateTime(this.createTime);
        order.setUpdateTime(this.updateTime);

        return order;
    }
}
