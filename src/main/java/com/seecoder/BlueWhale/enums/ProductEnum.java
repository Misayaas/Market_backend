package com.seecoder.BlueWhale.enums;

public class ProductEnum {
    public enum ProductType {
        /**
         * 产品类型:FOOD（食品），CLOTHES（服饰），FURNITURE（家具），ELECTRONICS（电子产品），ENTERTAINMENT（娱乐），SPORTS（体育产品），LUXURY（奢侈品）
         */
       FOOD, CLOTHES, FURNITURE, ELECTRONICS, ENTERTAINMENT, SPORTS, LUXURY,OTHER
    }

    public enum ProductStatus {
        /**
         * 产品状态
         */
        ON_SALE, OFF_SALE
    }
}
