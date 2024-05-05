package com.seecoder.BlueWhale.vo;

import com.seecoder.BlueWhale.po.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class StoreVO {
    private Integer id;
    private String storeName;
    private String logoUrl;
    private Integer adminId;
    private String description;
    private Date createTime;
    private Date updateTime;
    public Store toPO(){
        Store store = new Store();
        store.setId(this.id);
        store.setStoreName(this.storeName);
        store.setAdminId(this.adminId);
        store.setDescription(this.description);
        store.setLogoUrl(this.logoUrl);
        store.setCreateTime(this.createTime);
        store.setUpdateTime(this.updateTime);
        return store;
    }
}
