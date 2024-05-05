package com.seecoder.BlueWhale.service;

import com.seecoder.BlueWhale.vo.CouponGroupVO;
import com.seecoder.BlueWhale.vo.ResultVO;

import java.util.List;

public interface CouponGroupService {
    Boolean addCouponGroup(CouponGroupVO couponGroupVO);

    List<CouponGroupVO> getAllCouponGroup();

    List<CouponGroupVO> getStoreCouponGroup(Integer storeId);
}
