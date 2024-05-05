package com.seecoder.BlueWhale.serviceImpl.stragegy;

/*
 * “蓝鲸券”使用规则：
 * 0-100元区间打九五折；
 * 100-200元区间打九折；
 * 200-300元区间打八五折；
 * 300-400元区间打八折；
 * 400-500元区间打七五折；
 * 500元以上区间打七折。
*/


import org.springframework.stereotype.Service;

import java.util.NavigableMap;
import java.util.TreeMap;

@Service
public class SpecialCouponCalculateStrategy implements CalculateStrategy{
    private final NavigableMap<Double, Double> discountMap;

    public SpecialCouponCalculateStrategy(){
        this.discountMap = new TreeMap<>();
        discountMap.put(0.0, 0.95);
        discountMap.put(100.0, 0.9);
        discountMap.put(200.0, 0.85);
        discountMap.put(300.0, 0.8);
        discountMap.put(400.0, 0.75);
        discountMap.put(500.0, 0.7);
    }

    @Override
    public Double calculate(Double price) {
        Double total = 0.0;
        Double remainingPrice = price;

        for(Double key : discountMap.descendingKeySet()){
            if(remainingPrice >= key){
                Double currentPrice = remainingPrice - key >= 100 ? 100 : remainingPrice - key;
                total += currentPrice * discountMap.get(key);
                remainingPrice -= currentPrice;
            }
        }

        if(remainingPrice > 0){
            total += remainingPrice * 0.7;
        }
        return total;
    }

    //蓝鲸不会调用三参数
    public Double calculate(Double totalPrice, Double couponUsePrice, Double discountPrice){
        return 0.0;
    }
}
