package com.seecoder.BlueWhale.serviceImpl.stragegy;

import org.springframework.stereotype.Service;

@Service
public class DiscountCouponCalculateStrategy implements CalculateStrategy{
    @Override
    public Double calculate(Double price) {
        return 0.0;
    }

    public Double calculate(Double totalPrice, Double couponUsePrice, Double discount){
        if(totalPrice >= couponUsePrice){
            return totalPrice*discount;
        }else{
            return totalPrice;
        }
    }
}
