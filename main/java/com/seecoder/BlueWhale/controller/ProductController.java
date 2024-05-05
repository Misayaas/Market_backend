package com.seecoder.BlueWhale.controller;

import com.seecoder.BlueWhale.annotations.RequiresRoles;
import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.service.ProductService;
import com.seecoder.BlueWhale.vo.ProductVO;
import com.seecoder.BlueWhale.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequiresRoles({RoleEnum.STAFF, RoleEnum.CEO})
    @PostMapping("/register")
    public ResultVO<Boolean> createProduct(@RequestBody ProductVO productVO){
        return ResultVO.buildSuccess(productService.createProduct(productVO));
    }
    @RequiresRoles(RoleEnum.STAFF)
    @PostMapping("/update")
    public ResultVO<Boolean> updateProduct(@RequestBody ProductVO productVO){
        return ResultVO.buildSuccess(productService.updateProduct(productVO));
    }

    @GetMapping("/all")
    public ResultVO getAllProducts(){
        return ResultVO.buildSuccess(productService.getAllProducts());
    }

    @GetMapping("/storeAll/{storeId}")
    public ResultVO getStoreProducts(@PathVariable Integer storeId){
        return ResultVO.buildSuccess(productService.getStoreProducts(storeId));
    }

    @GetMapping("/{productId}")
    public ResultVO getProduct(@PathVariable Integer productId){
        return ResultVO.buildSuccess(productService.getProduct(productId));
    }

    @DeleteMapping("/delete/{productId}")
    public ResultVO<Boolean> deleteProduct(@PathVariable Integer productId){
        return ResultVO.buildSuccess(productService.deleteProduct(productId));
    }
}
