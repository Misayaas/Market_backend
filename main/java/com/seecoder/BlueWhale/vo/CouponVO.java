package com.seecoder.BlueWhale.vo;


import com.seecoder.BlueWhale.enums.CouponEnum;
import com.seecoder.BlueWhale.po.Coupon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CouponVO {
    private Integer id;
    private Integer groupId;
    private Integer userId;
    private Boolean isGlobal;
    //enum type to check the type of coupon
    private CouponEnum.CouponType type;//0满减券，1折扣券,2为特殊券（小蓝鲸券，直接无视以下词条计算）
    private Integer storeId;
    private Double discount;//满减券的折扣数值（比如100-29中的29）
    private Double threshold;//满减券的门槛（比如100-29中的100）这两个可为空
    private Double discountRate;//折扣券的折扣率（比如0.9代表9折）
    private Date createTime;
    private Date endTime;
    private Boolean isAvailable;//是否可用,根据时间和用户是否使用判断
    private Boolean isUsed;//是否已使用
    public Coupon toPO(){
        Coupon coupon = new Coupon();
        coupon.setId(this.id);
        coupon.setGroupId(this.groupId);
        coupon.setUserId(this.userId);
        coupon.setIsGlobal(this.isGlobal);
        coupon.setType(this.type);
        coupon.setStoreId(this.storeId);
        coupon.setDiscount(this.discount);
        coupon.setThreshold(this.threshold);
        coupon.setDiscountRate(this.discountRate);
        coupon.setCreateTime(this.createTime);
        coupon.setEndTime(this.endTime);
        coupon.setIsAvailable(this.isAvailable);
        coupon.setIsUsed(this.isUsed);
        return coupon;
    }
}
