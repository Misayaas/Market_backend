package com.seecoder.BlueWhale.service;

import com.seecoder.BlueWhale.po.Product;
import com.seecoder.BlueWhale.vo.ProductVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProductService {

    boolean createProduct(ProductVO productVO);

    boolean updateProduct(ProductVO productVO);

    //boolean deleteProduct(Integer ProductId);

    List<Product> getAllProducts();

    List<Product> getStoreProducts(Integer storeId);

    ProductVO getProduct(Integer productId);

    boolean deleteProduct(Integer productId);
}
