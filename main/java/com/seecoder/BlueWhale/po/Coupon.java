package com.seecoder.BlueWhale.po;
import com.seecoder.BlueWhale.enums.CouponEnum;
import com.seecoder.BlueWhale.vo.CommentVO;
import com.seecoder.BlueWhale.vo.CouponVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "group_id")
    private Integer groupId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "is_global")
    private Boolean isGlobal;
    @Basic
    @Column(name = "type")
    private CouponEnum.CouponType type;
    @Basic
    @Column(name = "store_id")
    private Integer storeId;
    @Basic
    @Column(name = "discount")
    private Double discount;
    @Basic
    @Column(name = "threshold")
    private Double threshold;
    @Basic
    @Column(name = "discount_rate")
    private Double discountRate;
    @Basic
    @Column(name = "create_time")
    private Date createTime;
    @Basic
    @Column(name = "end_time")
    private Date endTime;
    @Basic
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Basic
    @Column(name = "is_used")
    private Boolean isUsed;

   public CouponVO toVO() {
        CouponVO couponVO = new CouponVO();
        couponVO.setId(this.id);
        couponVO.setGroupId(this.groupId);
        couponVO.setUserId(this.userId);
        couponVO.setIsGlobal(this.isGlobal);
        couponVO.setType(this.type);
        couponVO.setStoreId(this.storeId);
        couponVO.setDiscount(this.discount);
        couponVO.setThreshold(this.threshold);
        couponVO.setDiscountRate(this.discountRate);
        couponVO.setCreateTime(this.createTime);
        couponVO.setEndTime(this.endTime);
        couponVO.setIsAvailable(this.isAvailable);
        couponVO.setIsUsed(this.isUsed);

        return couponVO;
    }
}
