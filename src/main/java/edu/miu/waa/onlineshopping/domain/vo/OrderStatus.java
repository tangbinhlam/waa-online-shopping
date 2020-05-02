package edu.miu.waa.onlineshopping.domain.vo;

public enum  OrderStatus {
    ORDER("ORDER"),
    PLACE_ORDER("PLACE_ORDER"),
    SHIPPED("SHIPPED"),
    DELEVERED("DELEVERED");

    private String orderStatus;
    private OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString(){
        return orderStatus;
    }

    public static OrderStatus of(String orderStatus) {
        return OrderStatus.valueOf(orderStatus);
    }
}
