package com.seecoder.BlueWhale.controller;

import com.seecoder.BlueWhale.annotations.RequiresRoles;
import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.service.OrderService;
import com.seecoder.BlueWhale.vo.OrderVO;
import com.seecoder.BlueWhale.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    OrderService orderservice;
    @GetMapping("/calculate")
    public ResultVO calculatePrice(@PathVariable Integer orderId,@PathVariable Integer couponId) {
        return ResultVO.buildSuccess(orderservice.calculatePrice(orderId, couponId));
    }

    @PostMapping("/create")
    public Integer createOrder(@RequestBody OrderVO orderVO){
        System.out.println("Creating order");
        return orderservice.createOrder(orderVO);
    }
    @PostMapping("/update")
    public ResultVO<Boolean> updateOrder(@RequestBody OrderVO orderVO){
        return ResultVO.buildSuccess(orderservice.updateOrder(orderVO));
    }

    @RequiresRoles({RoleEnum.CEO, RoleEnum.MANAGER})
    @GetMapping("/all")
    public ResultVO getAllOrders(){
        return ResultVO.buildSuccess(orderservice.getAllOrders());
    }
    @GetMapping("/{orderId}")
    public ResultVO getOrder(@PathVariable Integer orderId){
        return ResultVO.buildSuccess(orderservice.getOrder(orderId));
    }

    @RequiresRoles(RoleEnum.CUSTOMER)
    @GetMapping("/user/{userId}")
    public ResultVO getUserOrders(@PathVariable Integer userId){
        return ResultVO.buildSuccess(orderservice.getUserOrders(userId));
    }

    @RequiresRoles(RoleEnum.STAFF)
    @GetMapping("/store/{storeId}")
    public ResultVO getStoreOrders(@PathVariable Integer storeId){
        return ResultVO.buildSuccess(orderservice.getStoreOrders(storeId));
    }
    @DeleteMapping("/delete/{orderId}")
    public ResultVO<Boolean> deleteOrder(@PathVariable Integer orderId){
        return ResultVO.buildSuccess(orderservice.deleteOrder(orderId));
    }
    @RequiresRoles(RoleEnum.CUSTOMER)
    @PostMapping("/pay")
    public ResultVO<Boolean> payOrder(@RequestBody OrderVO orderVO){
        return ResultVO.buildSuccess(orderservice.payOrder(orderVO));
    }
    @RequiresRoles(RoleEnum.STAFF)
    @PostMapping("/deliver")
    public ResultVO<Boolean> deliverOrder(@RequestBody OrderVO orderVO){
        return ResultVO.buildSuccess(orderservice.deliverOrder(orderVO));
    }
    @RequiresRoles(RoleEnum.CUSTOMER)
    @PostMapping("/receive")
    public ResultVO<Boolean> receiveOrder(@RequestBody OrderVO orderVO){
        return ResultVO.buildSuccess(orderservice.receiveOrder(orderVO));
    }
    @RequiresRoles(RoleEnum.CUSTOMER)
    @PostMapping("/complete")
    public ResultVO<Boolean> completeOrder(@RequestBody OrderVO orderVO){
        return ResultVO.buildSuccess(orderservice.completeOrder(orderVO));
    }
    @RequiresRoles({RoleEnum.CUSTOMER, RoleEnum.STAFF})
    @PostMapping("/cancel")
    public ResultVO<Boolean> cancelOrder(@RequestBody OrderVO orderVO){
        return ResultVO.buildSuccess(orderservice.cancelOrder(orderVO));
    }
}
