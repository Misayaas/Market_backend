package com.seecoder.BlueWhale.serviceImpl;

import com.seecoder.BlueWhale.po.CouponGroup;
import com.seecoder.BlueWhale.repository.CouponGroupRepository;
import com.seecoder.BlueWhale.service.CouponGroupService;
import com.seecoder.BlueWhale.vo.CouponGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CouponGroupServiceImpl implements CouponGroupService {
    @Autowired
    CouponGroupRepository couponGroupRepository;

    @Override
    public Boolean addCouponGroup(CouponGroupVO couponGroupVO) {
        CouponGroup couponGroup = couponGroupVO.toPO();
        //couponGroup.setStartTime(new Date());
        couponGroup.setIsAvailable(true);
        couponGroup.setRemainNum(couponGroup.getTotalNum());
        couponGroupRepository.save(couponGroup);
        return true;
    }

    @Override
    public List<CouponGroupVO> getAllCouponGroup(){
        List<CouponGroup> couponGroupS =  couponGroupRepository.findAll();
        List<CouponGroupVO> couponGroupVOS = new ArrayList<>();
        for(CouponGroup couponGroup: couponGroupS){
            couponGroupVOS.add(couponGroup.toVO());
        }
        return couponGroupVOS;
    }


    @Override
    public List<CouponGroupVO> getStoreCouponGroup(Integer storeId){
        List<CouponGroup> couponGroupS =  couponGroupRepository.findByStoreId(storeId);
        List<CouponGroupVO> couponGroupVOS = new ArrayList<>();
        for(CouponGroup couponGroup: couponGroupS){
            couponGroupVOS.add(couponGroup.toVO());
        }
        return couponGroupVOS;
    }
}
