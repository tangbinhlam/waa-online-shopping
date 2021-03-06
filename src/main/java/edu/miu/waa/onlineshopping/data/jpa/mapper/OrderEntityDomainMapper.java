package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.OrderEntity;
import edu.miu.waa.onlineshopping.domain.model.Order;
import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityDomainMapper {

    private UserEntityDomainMapper userEntityDomainMapper;
    private AddressEntityDomainMapper addressEntityDomainMapper;
    private OrderItemEntityDomainMapper orderItemEntityDomainMapper;

    @Autowired
    public OrderEntityDomainMapper(UserEntityDomainMapper userEntityDomainMapper, AddressEntityDomainMapper addressEntityDomainMapper, OrderItemEntityDomainMapper orderItemEntityDomainMapper) {
        this.userEntityDomainMapper = userEntityDomainMapper;
        this.addressEntityDomainMapper = addressEntityDomainMapper;
        this.orderItemEntityDomainMapper = orderItemEntityDomainMapper;
    }


    public Order mapToDomain(OrderEntity orderEntity) {
        return (orderEntity != null) ?
                Order.of(orderEntity.getOrderId(),
                        orderEntity.getOrderDate(),
                        orderEntity.getShippedDate(),
                        orderEntity.getDeliveredDate(),
                        OrderStatus.of(orderEntity.getStatus()),
                        userEntityDomainMapper.mapToDomain(orderEntity.getOwner()),
                        userEntityDomainMapper.mapToDomain(orderEntity.getSeller()),
                        addressEntityDomainMapper.mapToDomain(orderEntity.getShipto()),
                        orderItemEntityDomainMapper.mapToDomains(orderEntity.getOrderItems()),
                        orderEntity.getTotal(),
                        orderEntity.getPointUsed(),
                        orderEntity.getPointEarned()
                ) : null;
    }

    public OrderEntity mapToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setShippedDate(order.getShippedDate());
        orderEntity.setDeliveredDate(order.getDeliveredDate());
        orderEntity.setStatus(order.getStatus().toString());
        orderEntity.setTotal(order.getTotal());
        orderEntity.setPointUsed(order.getPointUsed());
        orderEntity.setPointEarned(order.getPointEarned());
        orderEntity.setOrderItems(orderItemEntityDomainMapper.mapToEntities(order.getOrderItems()));
        orderEntity.setOwner(userEntityDomainMapper.mapToEntity(order.getOwner()));
        orderEntity.setSeller(userEntityDomainMapper.mapToEntity(order.getSeller()));
        orderEntity.setShipto(addressEntityDomainMapper.mapToEntity(order.getShipto()));
        return orderEntity;
    }
}
