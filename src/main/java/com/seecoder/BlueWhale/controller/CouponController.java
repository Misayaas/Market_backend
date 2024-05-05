package com.seecoder.BlueWhale.controller;



import com.seecoder.BlueWhale.service.CouponService;
import com.seecoder.BlueWhale.vo.CouponVO;
import com.seecoder.BlueWhale.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/coupons")
public class CouponController {
    @Autowired
    CouponService couponService;

    @PostMapping("/create")
    public ResultVO<Boolean> createCoupon(@RequestBody CouponVO couponVO){
        return ResultVO.buildSuccess(couponService.collectCoupon(couponVO));
    }

    @PostMapping("/delete")
    public ResultVO<Boolean> deleteCoupon(@RequestBody CouponVO couponVO){
        return ResultVO.buildSuccess(couponService.deleteCoupon(couponVO));
    }

    @GetMapping("/get")//method to get all coupons
    public ResultVO<List<CouponVO>> getAllCoupon(){
        return ResultVO.buildSuccess(couponService.getAllCoupon());
    }

    @GetMapping("/getUserCoupon")//method to get all coupons
    public ResultVO<List<CouponVO>> getUserCoupon(){
        return ResultVO.buildSuccess(couponService.getUserCoupon());
    }

    @GetMapping("/getInfo")
    public ResultVO<CouponVO> getCoupon(@RequestParam("coupon_id") Integer coupon_id){
        return ResultVO.buildSuccess(couponService.getCoupon(coupon_id));
    }
    @GetMapping("/isCollected")
    public ResultVO<Boolean> isCollected( @RequestParam("group_id") Integer group_id){
        return ResultVO.buildSuccess(couponService.isCollected(group_id));
    }
}
