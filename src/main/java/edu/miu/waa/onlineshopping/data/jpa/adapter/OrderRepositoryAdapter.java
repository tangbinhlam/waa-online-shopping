package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.entity.OrderEntity;
import edu.miu.waa.onlineshopping.data.jpa.mapper.OrderEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.OrderRepository;
import edu.miu.waa.onlineshopping.domain.model.Order;
import edu.miu.waa.onlineshopping.domain.repository.OrderDomainRepository;
import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class OrderRepositoryAdapter implements OrderDomainRepository {

    private final OrderEntityDomainMapper orderEntityDomainMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryAdapter(OrderEntityDomainMapper orderEntityDomainMapper, OrderRepository orderRepository) {
        this.orderEntityDomainMapper = orderEntityDomainMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderEntityDomainMapper.mapToDomain(orderRepository.save(orderEntityDomainMapper.mapToEntity(order)));
    }

    @Override
    public Order findOrderById(Integer orderId) {
        return orderEntityDomainMapper.mapToDomain(orderRepository.findById(orderId).get());
    }

    @Override
    public Order cancelOrder(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.CANCEL.toString());
        return orderEntityDomainMapper.mapToDomain(orderRepository.save(orderEntity));
    }

    @Override
    public Order rejectOrder(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.REJECT.toString());
        return orderEntityDomainMapper.mapToDomain(orderRepository.save(orderEntity));
    }

    @Override
    public Order deliveredOrder(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.DELIVERED.toString());
        orderEntity.setDeliveredDate(LocalDate.now());
        return orderEntityDomainMapper.mapToDomain(orderRepository.save(orderEntity));
    }

    @Override
    public Order approveOrder(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.SHIPPED.toString());
        orderEntity.setShippedDate(LocalDate.now());
        return orderEntityDomainMapper.mapToDomain(orderRepository.save(orderEntity));
    }

    @Override
    public List<Order> findAll() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(orderEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderBySeller(Integer sellerId) {
        return orderRepository.findOrderBySeller(sellerId).stream().map(orderEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderHistory(Integer buyerId) {
        return orderRepository.findOrderHistory(buyerId).stream().map(orderEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }
}
