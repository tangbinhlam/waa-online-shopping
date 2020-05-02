package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.Order;
import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;

import java.util.List;

public interface OrderDomainRepository {
    Order save(Order order);
    Order changeOrderStatus(Integer orderId, OrderStatus orderStatus);
    boolean cancelOrder(Integer orderId);
    List<Order> findAll();
}
