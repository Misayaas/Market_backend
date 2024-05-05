package com.seecoder.BlueWhale.serviceImpl.stragegy;

import org.springframework.stereotype.Service;

@Service
public interface CalculateStrategy {
    Double calculate(Double price);
    Double calculate(Double totalPrice, Double couponUsePrice, Double discountPrice);



}
