package com.seecoder.BlueWhale.controller;


import com.seecoder.BlueWhale.annotations.RequiresRoles;

import com.seecoder.BlueWhale.po.CouponGroup;
import com.seecoder.BlueWhale.service.CouponService;
import com.seecoder.BlueWhale.vo.CouponGroupVO;
import com.seecoder.BlueWhale.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.seecoder.BlueWhale.annotations.RequiresRoles;
import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.service.CouponGroupService;

import java.util.List;

@RestController
@RequestMapping("api/couponGroups")
public class CouponGroupController {
        @Autowired
        CouponGroupService couponGroupService;


        @RequiresRoles({RoleEnum.CEO, RoleEnum.STAFF})
        @PostMapping("/create")
        public ResultVO<Boolean> createCouponGroup(@RequestBody CouponGroupVO couponGroupVO){
            return ResultVO.buildSuccess(couponGroupService.addCouponGroup(couponGroupVO));
        }

        @RequiresRoles({RoleEnum.CEO, RoleEnum.MANAGER})
        @PostMapping("/delete")
        public ResultVO<Boolean> deleteCouponGroup(){
            return ResultVO.buildSuccess(true);
        }


        @GetMapping("/get")
        public ResultVO<List<CouponGroupVO>> getCouponGroup(){
                return ResultVO.buildSuccess(couponGroupService.getAllCouponGroup());
        }

        @GetMapping("/getStoreCouponGroup")
        public ResultVO<List<CouponGroupVO>> getStoreCouponGroup(@RequestParam("store_id") Integer store_id){
                return ResultVO.buildSuccess(couponGroupService.getStoreCouponGroup(store_id));
        }
}
