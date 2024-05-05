package com.seecoder.BlueWhale.serviceImpl;

import com.seecoder.BlueWhale.po.Coupon;
import com.seecoder.BlueWhale.po.CouponGroup;
import com.seecoder.BlueWhale.po.User;
import com.seecoder.BlueWhale.repository.CouponGroupRepository;
import com.seecoder.BlueWhale.repository.CouponRepository;
import com.seecoder.BlueWhale.repository.UserRepository;
import com.seecoder.BlueWhale.service.CouponService;
import com.seecoder.BlueWhale.util.SecurityUtil;
import com.seecoder.BlueWhale.vo.CouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CouponGroupRepository couponGroupRepository;
    @Autowired
    SecurityUtil securityUtil;

    @Override
    public Boolean collectCoupon(CouponVO couponVO) {
        Coupon coupon = couponVO.toPO();
        coupon.setCreateTime(new Date());
        coupon.setIsAvailable(true);
        coupon.setStatus(0);
        Optional<CouponGroup> optionalCouponGroup = couponGroupRepository.findById(coupon.getGroupId());
        if(!optionalCouponGroup.isPresent()){
            return false;
        }
        int userId = securityUtil.getCurrentUser().getId();
        coupon.setUserId(userId);
        //避免用户重复领取同一张优惠券
        if(!couponRepository.findByUserIdAndGroupId(userId, coupon.getGroupId()).isEmpty()){
            return false;
        }
        CouponGroup couponGroup = optionalCouponGroup.get();
        coupon.setEndTime(couponGroup.getEndTime());
        coupon.setType(couponGroup.getType());
        coupon.setDiscount(couponGroup.getDiscount());
        coupon.setThreshold(couponGroup.getThreshold());
        coupon.setIsGlobal(couponGroup.getIsGlobal());
        coupon.setStoreId(couponGroup.getStoreId());
        couponGroup.setRemainNum(couponGroup.getRemainNum()-1);
        couponGroupRepository.save(couponGroup);
        couponRepository.save(coupon);
        return true;
    }

    @Override
    public Boolean deleteCoupon(CouponVO couponVO) {
        Coupon coupon = couponVO.toPO();
        couponRepository.deleteById(coupon.getId());
        return true;
    }

    @Override
    public List<CouponVO> getAllCoupon() {
        updateCouponStatusByTime();
        List<CouponVO> couponVOList = new ArrayList<>();
        int len = couponRepository.findAll().size();
        for(int i = 0; i < len; i++){
            CouponVO temp =couponRepository.findAll().get(i).toVO();
            Date cur = new Date();
            if(cur.after(temp.getEndTime()))
                temp.setIsAvailable(false);
            couponVOList.add(temp);
        }
        return couponVOList;
    }

    @Override
    public List<CouponVO> getUserCoupon() {
        updateCouponStatusByTime();
        Integer userId = securityUtil.getCurrentUser().getId();
        List<CouponVO> couponVOList = new ArrayList<>();
        int len = couponRepository.findByUserId(userId).size();
        for(int i = 0; i < len; i++){
            couponVOList.add(couponRepository.findByUserId(userId).get(i).toVO());
        }
        return couponVOList;
    }

    public CouponVO getCoupon(Integer couponId) {
        updateCouponStatusByTime();
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        return optionalCoupon.map(Coupon::toVO).orElse(null);
    }

    public void updateCouponStatusByTime() {
        List<Coupon> coupons = couponRepository.findAll();
        for (Coupon coupon : coupons) {
            if (coupon.getEndTime().before(new Date())) {
                coupon.setIsAvailable(false);
                couponRepository.save(coupon);
            }
        }
    }

    @Override
    public Boolean isCollected( Integer groupId) {
        Integer userId = securityUtil.getCurrentUser().getId();
        return !couponRepository.findByUserIdAndGroupId(userId, groupId).isEmpty();
    }

}
