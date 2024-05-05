package com.seecoder.BlueWhale.serviceImpl;

import com.seecoder.BlueWhale.po.Store;

import com.seecoder.BlueWhale.po.User;
import com.seecoder.BlueWhale.repository.ProductRepository;
import com.seecoder.BlueWhale.repository.StoreRepository;
import com.seecoder.BlueWhale.service.StoreService;
import com.seecoder.BlueWhale.service.UserService;
import com.seecoder.BlueWhale.vo.StoreVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;
    @Override
    public Boolean createStore(StoreVO storeVO) {
        Store store = storeRepository.findByStoreName(storeVO.getStoreName());
        if (store != null) {
            throw new RuntimeException("Store already exists");
        }

        Store newStore = storeVO.toPO();
        Integer adminId = userService.getInformation().getId();
        //set the store id of the user
        User user = userService.getInformation().toPO();
        user.setStoreId(newStore.getId());
        userService.updateInformation(user.toVO());
        newStore.setAdminId(adminId);
        newStore.setCreateTime(new Date());
        newStore.setUpdateTime(new Date());
        //logger.info("Store created: " + newStore.toString());
        try {
            storeRepository.save(newStore);
        } catch (Exception e) {
            //logger.error("Failed to create store: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateInformation(StoreVO storeVO) {
        //logger.info("Updating store: " + storeVO.toString());
        Store store = storeRepository.findByStoreName(storeVO.getStoreName());
        if (store == null) {
            throw new RuntimeException("Store does not exist");
        }
        //check if store admin fits
        Integer adminId = userService.getInformation().getId();
        if (store.getAdminId() != adminId) {
            throw new RuntimeException("User is not the admin of the store");
        }
        store.setStoreName(storeVO.getStoreName());
        store.setLogoUrl(storeVO.getLogoUrl());
        store.setDescription(storeVO.getDescription());
        store.setUpdateTime(new Date());

        try {
            storeRepository.save(store);
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public String findStoreName(Integer storeId) {
        Optional<Store> optionalStore= storeRepository.findById(storeId);
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            return store.getStoreName();
            // do something with store
        } else {
            return null;
            // handle case where no Store was found
        }
    }

    public Boolean deleteStore(Integer storeId) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);
        //TODO: confirm whether the user who deletes the store is the admin of the store
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            storeRepository.delete(store);
            return true;
        } else {
            return false;
        }
    }

    public Float getStoreScore(Integer storeId) {
        Double score = productRepository.findAverageScoreByStoreId(storeId);
        if(score != null)
        {
            return score.floatValue();
        }
        else {
            return 0f;
        }
    }
}
