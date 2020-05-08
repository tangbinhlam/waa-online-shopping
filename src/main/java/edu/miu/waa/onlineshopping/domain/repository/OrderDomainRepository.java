package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.Order;
import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;

import java.util.List;

public interface OrderDomainRepository {
    Order save(Order order);
    Order findOrderById(Integer orderId);
    Order cancelOrder(Integer orderId);
    Order rejectOrder(Integer orderId);
    Order deliveredOrder(Integer orderId);
    Order approveOrder(Integer orderId);
    List<Order> findAll();
    List<Order> findOrderBySeller(Integer sellerId);
    List<Order> findOrderHistory(Integer buyer);
}
