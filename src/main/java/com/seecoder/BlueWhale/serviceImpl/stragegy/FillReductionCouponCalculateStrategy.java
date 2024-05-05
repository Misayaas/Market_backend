package com.seecoder.BlueWhale.serviceImpl.stragegy;

import org.springframework.stereotype.Service;

@Service
public class FillReductionCouponCalculateStrategy implements CalculateStrategy{
    @Override
    //满减不会调用单参数
    public Double calculate(Double price) {
        return 0.0;
    }

    public Double calculate(Double totalPrice, Double couponUsePrice, Double discountPrice){
        if(totalPrice >= couponUsePrice){
            return totalPrice - discountPrice;
        }
        return totalPrice;
    }
}
