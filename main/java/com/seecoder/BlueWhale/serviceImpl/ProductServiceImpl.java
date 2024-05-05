package com.seecoder.BlueWhale.serviceImpl;

import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.po.Product;
import com.seecoder.BlueWhale.repository.ProductRepository;
import com.seecoder.BlueWhale.service.ProductService;
import com.seecoder.BlueWhale.service.StoreService;
import com.seecoder.BlueWhale.service.UserService;
import com.seecoder.BlueWhale.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserService userService;
    private Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public boolean createProduct(ProductVO productVO) {
        logger.info("Creating product: " + productVO.toString());
        //List<Product> product = productRepository.findByProductName(productVO.getName());
        Integer storeid = userService.getInformation().getStoreId();
        RoleEnum role = userService.getInformation().getRole();
        if (role != RoleEnum.STAFF) {
            logger.error("User is not the admin of the product");
            return false;
        }
        Product newProduct = productVO.toPO();

        Integer storeId = userService.getInformation().getStoreId();
        newProduct.setStoreId(storeId);
        newProduct.setStock(0);
        newProduct.setCreateTime(new Date());
        newProduct.setUpdateTime(new Date());
        logger.info("Product created: " + newProduct.toString());
        try{
            productRepository.save(newProduct);
        }catch(Exception e){
            logger.error("Failed to create product: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateProduct(ProductVO productVO){
        logger.info("Updating product: " + productVO.toString());
        Optional<Product> optionalProduct = productRepository.findById(productVO.getId());
        if (!optionalProduct.isPresent()) {
            logger.error("Product not found");
            return false;
        }
        //check the user is the owner of the product and his status should be STAFF
        Integer storeId = userService.getInformation().getStoreId();
        RoleEnum role = userService.getInformation().getRole();
        if (optionalProduct.get().getStoreId() != storeId|| role != RoleEnum.STAFF) {
            logger.error("User is not the admin of the product");
            return false;
        }
        Product product = optionalProduct.get();
        product.setName(productVO.getName());
        product.setPrice(productVO.getPrice());
        product.setStock(productVO.getStock());
        product.setUpdateTime(new Date());
        try{
            productRepository.save(product);
        }catch(Exception e){
            logger.error("Failed to update product: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
    if (!optionalProduct.isPresent()) {
            logger.error("Product not found");
            return false;
        }
        Product product = optionalProduct.get();
        try{
            productRepository.delete(product);
        }catch(Exception e){
            logger.error("Failed to delete product: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> getStoreProducts(Integer storeId) {
        List<Product> products = productRepository.findAllByStoreId(storeId);
        return products;
    }

    @Override
    public ProductVO getProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product.toVO();
        } else {
            return null;
        }
    }
}
