package com.seecoder.BlueWhale.repository;

import com.seecoder.BlueWhale.po.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByAdminId(Integer adminId);
    Store findByStoreName(String storeName);
    Optional<Store> findById(Integer storeId);
    List<Store> findAll();

}