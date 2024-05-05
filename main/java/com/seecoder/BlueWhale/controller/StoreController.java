package com.seecoder.BlueWhale.controller;

import com.seecoder.BlueWhale.annotations.RequiresRoles;
import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.po.Store;
import com.seecoder.BlueWhale.repository.StoreRepository;
import com.seecoder.BlueWhale.service.StoreService;
import com.seecoder.BlueWhale.util.SecurityUtil;
import com.seecoder.BlueWhale.vo.ResultVO;
import com.seecoder.BlueWhale.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    SecurityUtil securityUtil;

    @RequiresRoles(RoleEnum.MANAGER)
    @PostMapping("/register")
    public ResultVO<Boolean> createStore(@RequestBody StoreVO storeVO){
        //check the user identity
        RoleEnum role = securityUtil.getCurrentUser().getRole();
        return ResultVO.buildSuccess(storeService.createStore(storeVO));
    }
    @GetMapping("/all")
    public List<Store> getAllStores(){
        List<Store> stores;
        try {
            stores = storeService.getAllStores();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return storeService.getAllStores();
    }

    @RequiresRoles({RoleEnum.CEO, RoleEnum.MANAGER, RoleEnum.STAFF})
   @PostMapping("/update")
   public ResultVO<Boolean> updateinformation(@RequestBody StoreVO storeVO){
        return ResultVO.buildSuccess(storeService.updateInformation(storeVO));
   }

   @GetMapping("/findName/{storeId}")
    public String findStoreName(@PathVariable Integer storeId){
        return storeService.findStoreName(storeId);
    }

    @GetMapping("/findStore/{storeId}")
    public Store findById(@PathVariable Integer storeId){
        Optional<Store> optionalStore = storeRepository.findById(storeId);

        return optionalStore.orElse(null);
    }

    @RequiresRoles(RoleEnum.CEO)
    @DeleteMapping("/delete")
    public ResultVO<Boolean> deleteStore(@RequestParam("storeId") Integer storeId){
        return ResultVO.buildSuccess(storeService.deleteStore(storeId));
    }

    @GetMapping("/score/{storeId}")
    public ResultVO<Float> getStoreScore(@PathVariable Integer storeId){
        return ResultVO.buildSuccess(storeService.getStoreScore(storeId));
    }

}
