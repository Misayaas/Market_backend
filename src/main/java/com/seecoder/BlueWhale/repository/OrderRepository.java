package com.seecoder.BlueWhale.repository;

import com.seecoder.BlueWhale.po.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAll();
    List<Order> findAllByUserId(Integer userId);
    List<Order> findAllByStoreId(Integer storeId);
    Optional<Order> findById(Integer id);
}
