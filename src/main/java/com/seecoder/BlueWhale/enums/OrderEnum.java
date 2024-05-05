package com.seecoder.BlueWhale.enums;

public class OrderEnum {
    public enum PickUpMethod{
         DELIVERY,PICKUP
    }

    public enum OrderStatus{
        /**
         * 订单状态
         */
        UNPAID,UNDELIVERED,UNRECEIVED,UNCOMMENTED,COMPLETED,CANCELED,RETURNED
    }
}
