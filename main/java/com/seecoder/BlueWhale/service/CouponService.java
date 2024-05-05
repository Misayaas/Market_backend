package com.seecoder.BlueWhale.service;

import com.seecoder.BlueWhale.vo.CouponVO;

import java.util.List;
import java.util.Optional;

public interface CouponService{
    Boolean collectCoupon(CouponVO couponVO);

    Boolean deleteCoupon(CouponVO couponVO);

    List<CouponVO> getAllCoupon();

    List<CouponVO> getUserCoupon();

    CouponVO getCoupon(Integer couponId);

    Boolean isCollected(Integer groupId);
}
