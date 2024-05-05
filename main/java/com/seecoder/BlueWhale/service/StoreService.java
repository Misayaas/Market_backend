package com.seecoder.BlueWhale.service;

import com.seecoder.BlueWhale.po.Store;
import com.seecoder.BlueWhale.vo.StoreVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {
    Boolean createStore(StoreVO storeVO);

    List<Store> getAllStores();

    Boolean updateInformation(StoreVO storeVO);

    String findStoreName(Integer storeId);

    Boolean deleteStore(Integer storeId);

    Float getStoreScore(Integer storeId);
}
