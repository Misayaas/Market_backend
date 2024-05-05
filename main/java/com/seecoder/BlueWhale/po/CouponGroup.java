package com.seecoder.BlueWhale.po;
import com.seecoder.BlueWhale.enums.CouponEnum;
import com.seecoder.BlueWhale.vo.CouponGroupVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "coupon_group")
public class CouponGroup{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "type")
    private CouponEnum.CouponType type;//0满减券，1折扣券,2为特殊券（小蓝鲸券，直接无视以下词条计算）
    @Basic
    @Column(name = "is_global")
    private Boolean isGlobal;
    @Basic
    @Column(name = "store_id")
    private Integer storeId;
    @Basic
    @Column(name = "discount")
    private Double discount;//满减券的折扣数值（比如100-29中的29）
    @Basic
    @Column(name = "threshold")
    private Double threshold;//满减券的门槛（比如100-29中的100）这两个可为空
    @Basic
    @Column(name = "discount_rate")
    private Double discountRate;//折扣券的折扣率（比如0.9代表9折）
    @Basic
    @Column(name = "total_num")
    private Integer totalNum;//总数
    @Basic
    @Column(name = "remain_num")
    private Integer remainNum;//剩余数
    @Basic
    @Column(name = "start_time")
    private Date startTime;
    @Basic
    @Column(name = "end_time")
    private Date endTime;
    @Basic
    @Column(name = "is_available")
    private Boolean isAvailable;//是否可用,根据时间和数量判断

    public CouponGroupVO toVO(){
        CouponGroupVO couponGroupVO = new CouponGroupVO();
        couponGroupVO.setId(this.id);
        couponGroupVO.setType(this.type);
        couponGroupVO.setIsGlobal(this.isGlobal);
        couponGroupVO.setStoreId(this.storeId);
        couponGroupVO.setDiscount(this.discount);
        couponGroupVO.setThreshold(this.threshold);
        couponGroupVO.setDiscountRate(this.discountRate);
        couponGroupVO.setTotalNum(this.totalNum);
        couponGroupVO.setRemainNum(this.remainNum);
        couponGroupVO.setStartTime(this.startTime);
        couponGroupVO.setEndTime(this.endTime);
        couponGroupVO.setIsAvailable(this.isAvailable);
        return couponGroupVO;
    }


}
