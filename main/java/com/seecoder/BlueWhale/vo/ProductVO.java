package com.seecoder.BlueWhale.vo;


import com.seecoder.BlueWhale.enums.ProductEnum;
import com.seecoder.BlueWhale.po.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductVO {
    private Integer id;
    private String name;
    private Integer storeId;
    @NotEmpty(message = "Price can't be empty")
    private Double price;
    @NotEmpty(message = "Stock can't be empty")
    private Integer stock;
    private ProductEnum.ProductType type;
    @NotEmpty(message = "Description can't be empty")
    private String description;
    @NotNull(message = "imageUrl can't be null")
    private List<String> imageUrl;
    private Float score;
    private Date createTime;
    private Date updateTime;

    public Product toPO(){
        Product product = new Product();
        product.setName(this.name);
        product.setStoreId(this.storeId);
        product.setDescription(this.description);
        product.setType(this.type);
        if(this.price == null){
            this.price = 0.0;
        }
        product.setPrice(this.price);
        //set List to PO
        product.setImageUrl(this.imageUrl);
        product.setStock(this.stock);
        product.setScore(this.score);
        product.setCreateTime(this.createTime);
        product.setUpdateTime(this.updateTime);
        product.setId(this.id);
        return product;
    }


}
