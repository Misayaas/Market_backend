package com.seecoder.BlueWhale.po;

import com.seecoder.BlueWhale.vo.StoreVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import javax.persistence.*;
import java.util.Date;
import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Store {
    //Logger logger = Logger.getLogger("Store");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "storeName", nullable = false)
    private String storeName;
    @Basic
    @Column(name = "logoUrl", nullable = false)
    private String logoUrl;
    @Basic
    @Column(name = "admin_id", nullable = false)
    private Integer adminId;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    public StoreVO toVO(){
        StoreVO storeVO = new StoreVO();
        storeVO.setId(this.id);
        storeVO.setStoreName(this.storeName);
        //storeVO.setAdminId(this.adminId);
//        logger.info("adminId: " + this.description);
        storeVO.setDescription(this.description);
        storeVO.setLogoUrl(this.logoUrl);
        storeVO.setCreateTime(this.createTime);
        storeVO.setUpdateTime(this.updateTime);
        return storeVO;
    }



}
