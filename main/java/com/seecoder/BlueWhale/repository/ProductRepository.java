package com.seecoder.BlueWhale.repository;
import java.util.List;
import java.util.Optional;

import com.seecoder.BlueWhale.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByStoreId(Integer storeId);
    List<Product> findByName(String name);
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    long count();

    @Query("SELECT AVG(p.score) FROM Product p WHERE p.storeId = :storeId AND p.score != 0.0")
    Double findAverageScoreByStoreId(@Param("storeId") Integer storeId);
}
