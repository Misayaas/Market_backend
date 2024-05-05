package com.seecoder.BlueWhale.po;


import com.seecoder.BlueWhale.enums.ProductEnum;
import com.seecoder.BlueWhale.vo.ProductVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProductEnum.ProductType type;

    @Basic
    @Column(name = "price")
    private Double price;

    @Basic
    @Column(name = "store_id")
    private Integer storeId;

    @Basic
    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "product_image_url", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> imageUrl;

    @Basic
    @Column(name = "stock")
    private Integer stock;

    @Basic
    @Column(name = "create_time")
    private Date createTime;

    @Basic
    @Column(name = "update_time")
    private Date updateTime;

    @Basic
    @Column(name = "score")
    private Float score;

    public ProductVO toVO(){
        ProductVO productVO = new ProductVO();
        productVO.setPrice(this.price);
        productVO.setName(this.name);
        productVO.setStoreId(this.storeId);
        productVO.setDescription(this.description);
        productVO.setStock(this.stock);
        productVO.setType(this.type);
        productVO.setImageUrl(this.imageUrl);
        productVO.setId(this.id);
        if(this.score == null){
            this.score = 0f;
        }
        productVO.setScore(this.score);
        productVO.setCreateTime(this.createTime);
        productVO.setUpdateTime(this.updateTime);
        return productVO;
    }
}
