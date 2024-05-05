package com.seecoder.BlueWhale.repository;

import com.seecoder.BlueWhale.po.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByUserId(Integer userId);
    List<Coupon> findAll();
    Optional<Coupon> findById(Integer id);
    Collection<Object> findByUserIdAndGroupId(int userId, Integer groupId);
}
