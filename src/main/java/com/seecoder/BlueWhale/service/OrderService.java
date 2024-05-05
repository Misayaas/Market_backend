package com.seecoder.BlueWhale.service;

import com.seecoder.BlueWhale.po.Order;
import com.seecoder.BlueWhale.vo.OrderVO;

import java.util.List;

public interface OrderService {
    Double calculatePrice(Integer orderId, Integer couponId);
    Integer createOrder(OrderVO orderVO);
    boolean updateOrder(OrderVO orderVO);
    OrderVO getOrder(Integer orderId);
    List<Order> getUserOrders(Integer userId);
    List<Order> getStoreOrders(Integer storeId);
    List<Order> getAllOrders();
    boolean deleteOrder(Integer orderId);
    boolean payOrder(OrderVO orderVO);
    //订单发货
    boolean deliverOrder(OrderVO orderVO);
    //订单收货
    boolean receiveOrder(OrderVO orderVO);
    //订单完成
    boolean completeOrder(OrderVO orderVO);
    //取消订单
    boolean cancelOrder(OrderVO orderVO);


}
