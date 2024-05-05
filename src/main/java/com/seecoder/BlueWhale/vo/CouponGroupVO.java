package com.seecoder.BlueWhale.vo;

import com.seecoder.BlueWhale.enums.CouponEnum;
import com.seecoder.BlueWhale.po.CouponGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CouponGroupVO {
    private Integer id;
    private CouponEnum.CouponType type;//0满减券，1折扣券,2为特殊券（小蓝鲸券，直接无视以下词条计算）
    private Boolean isGlobal;
    private Integer storeId;
    private Double discount;//满减券的折扣数值（比如100-29中的29）
    private Double threshold;//满减券的门槛（比如100-29中的100）这两个可为空
    private Double discountRate;//折扣券的折扣率（比如0.9代表9折）
    private Integer totalNum;//总数
    private Integer remainNum;//剩余数
    private Date startTime;
    private Date endTime;
    private Boolean isAvailable;//是否可用,根据时间和数量判断

    public CouponGroup toPO(){
        CouponGroup couponGroup = new CouponGroup();
        couponGroup.setId(this.id);
        couponGroup.setType(this.type);
        couponGroup.setIsGlobal(this.isGlobal);
        couponGroup.setStoreId(this.storeId);
        couponGroup.setDiscount(this.discount);
        couponGroup.setThreshold(this.threshold);
        couponGroup.setDiscountRate(this.discountRate);
        couponGroup.setTotalNum(this.totalNum);
        couponGroup.setRemainNum(this.remainNum);
        couponGroup.setStartTime(this.startTime);
        couponGroup.setEndTime(this.endTime);
        couponGroup.setIsAvailable(this.isAvailable);
        return couponGroup;
    }
}
