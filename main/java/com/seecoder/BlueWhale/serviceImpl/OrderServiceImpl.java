package com.seecoder.BlueWhale.serviceImpl;

import com.seecoder.BlueWhale.enums.CouponEnum;
import com.seecoder.BlueWhale.enums.OrderEnum;
import com.seecoder.BlueWhale.enums.RoleEnum;
import com.seecoder.BlueWhale.po.Order;
import com.seecoder.BlueWhale.repository.CouponRepository;
import com.seecoder.BlueWhale.repository.OrderRepository;
import com.seecoder.BlueWhale.service.CouponService;
import com.seecoder.BlueWhale.service.OrderService;
import com.seecoder.BlueWhale.service.UserService;
import com.seecoder.BlueWhale.serviceImpl.stragegy.CalculateStrategy;
import com.seecoder.BlueWhale.serviceImpl.stragegy.DiscountCouponCalculateStrategy;
import com.seecoder.BlueWhale.serviceImpl.stragegy.FillReductionCouponCalculateStrategy;
import com.seecoder.BlueWhale.serviceImpl.stragegy.SpecialCouponCalculateStrategy;
import com.seecoder.BlueWhale.vo.CouponVO;
import com.seecoder.BlueWhale.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    UserService userService;
    @Autowired
    CouponService couponService;
//    @Autowired
//    FillReductionCouponCalculateStrategy fillReductionCouponCalculateStrategy;
//    @Autowired
//    SpecialCouponCalculateStrategy specialCouponCalculateStrategy;

    CalculateStrategy calculateStrategy;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public Double calculatePrice(Integer orderId,Integer couponId) {
        OrderVO orderVO = getOrder(orderId);
        CouponVO couponVO = couponService.getCoupon(couponId);
        logger.info("Calculating price: " + orderVO.toString());
        Double price = orderVO.getTotalPrice();
        if(couponVO.getType() == CouponEnum.CouponType.FULL_REDUCTION){
            this.calculateStrategy = new FillReductionCouponCalculateStrategy();
            price = calculateStrategy.calculate(price, couponVO.getThreshold(), couponVO.getDiscount());
        }
        else if(couponVO.getType() == CouponEnum.CouponType.BLUE_WHALE){
            this.calculateStrategy = new SpecialCouponCalculateStrategy();
            price = calculateStrategy.calculate(price);
        }
        else if(couponVO.getType() == CouponEnum.CouponType.DISCOUNT){
            this.calculateStrategy = new DiscountCouponCalculateStrategy();
            price = calculateStrategy.calculate(price);
        }

        return price;
    }
    public Integer createOrder(OrderVO orderVO) {
        logger.info("Creating order: " + orderVO.toString());
        RoleEnum role = userService.getInformation().getRole();
        if (role != RoleEnum.CUSTOMER && role != RoleEnum.STAFF) {
            logger.error("User is not a customer OR staff");
            return -1;
        }
        Order newOrder = orderVO.toPO();
        if (newOrder.getCouponId() == null) {
            newOrder.setCouponId(0);
        }
        newOrder.setUserId(userService.getInformation().getId());
        newOrder.setOrderStatus(OrderEnum.OrderStatus.UNPAID);
        newOrder.setCreateTime(new Date());
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to create order: " + e.getMessage());
            return -1;
        }
        return newOrder.getId();
    }

    public boolean updateOrder(OrderVO orderVO) {
        logger.info("Updating order: " + orderVO.toString());
        Optional<Order> optionalOrder = orderRepository.findById(orderVO.getId());
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }

        RoleEnum role = userService.getInformation().getRole();
//        if(role != RoleEnum.CUSTOMER){
//            logger.error("User is not a customer");
//            return false;
//        }
        Order newOrder = optionalOrder.get();

        newOrder.setOrderStatus(orderVO.getOrderStatus());
        newOrder.setComment(orderVO.getComment());
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to update order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }
        Order order = optionalOrder.get();
        try {
            orderRepository.delete(order);
        } catch (Exception e) {
            logger.error("Failed to delete order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public OrderVO getOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get().toVO();
        } else {
            return null;
        }
    }

    public boolean payOrder(OrderVO orderVO) {
        logger.info("Updating order: " + orderVO.toString());
        Optional<Order> optionalOrder = orderRepository.findById(orderVO.getId());
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }
        Order newOrder = optionalOrder.get();
        newOrder.setOrderStatus(OrderEnum.OrderStatus.UNDELIVERED);
        newOrder.setTotalPrice(orderVO.getTotalPrice());
        newOrder.setCouponId(orderVO.getCouponId());
        //delete the coupon
        if(orderVO.getCouponId() != null){
            //set the coupon is_used to true
            CouponVO couponVO = couponService.getCoupon(orderVO.getCouponId());
            couponVO.setIsUsed(true);
            couponRepository.save(couponVO.toPO());
        }
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to pay order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deliverOrder(OrderVO orderVO) {
        logger.info("Delivering order: " + orderVO.toString());
        Optional<Order> optionalOrder = orderRepository.findById(orderVO.getId());
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }
        Order newOrder = optionalOrder.get();
        newOrder.setOrderStatus(OrderEnum.OrderStatus.UNRECEIVED);
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to deliver order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean receiveOrder(OrderVO orderVO) {
        logger.info("Receiving order: " + orderVO.toString());
        Optional<Order> optionalOrder = orderRepository.findById(orderVO.getId());
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }
        Order newOrder = optionalOrder.get();
        newOrder.setOrderStatus(OrderEnum.OrderStatus.UNCOMMENTED);
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to receive order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean completeOrder(OrderVO orderVO) {
        logger.info("Finishing order: " + orderVO.toString());
        Optional<Order> optionalOrder = orderRepository.findById(orderVO.getId());
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }
        Order newOrder = optionalOrder.get();
        newOrder.setOrderStatus(OrderEnum.OrderStatus.COMPLETED);
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to finish order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean cancelOrder(OrderVO orderVO) {
        logger.info("Cancelling order: " + orderVO.toString());
        Optional<Order> optionalOrder = orderRepository.findById(orderVO.getId());
        if (!optionalOrder.isPresent()) {
            logger.error("Order not found");
            return false;
        }
        Order newOrder = optionalOrder.get();
        newOrder.setOrderStatus(OrderEnum.OrderStatus.CANCELED);
        newOrder.setUpdateTime(new Date());
        try {
            orderRepository.save(newOrder);
        } catch (Exception e) {
            logger.error("Failed to cancel order: " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Order> getUserOrders(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getStoreOrders(Integer storeId) {
        return orderRepository.findAllByStoreId(storeId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
