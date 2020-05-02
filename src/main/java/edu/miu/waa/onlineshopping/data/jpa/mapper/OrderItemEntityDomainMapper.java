package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.OrderItemEntity;
import edu.miu.waa.onlineshopping.domain.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemEntityDomainMapper {

    private ProductEntityDomainMapper productEntityDomainMapper;

    @Autowired
    public OrderItemEntityDomainMapper(ProductEntityDomainMapper productEntityDomainMapper) {
        this.productEntityDomainMapper = productEntityDomainMapper;
    }

    public OrderItem mapToDomain(OrderItemEntity orderItemEntity) {
        return (orderItemEntity != null) ?
                OrderItem.of(orderItemEntity.getOrderItemId(),
                        orderItemEntity.getQuantity(),
                        orderItemEntity.getPrice(),
                        productEntityDomainMapper.mapToDomain(orderItemEntity.getProduct())
                ) : null;
    }

    public OrderItemEntity mapToEntity(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderItemId(orderItem.getOrderItemId());
        orderItemEntity.setPrice(orderItem.getPrice());
        orderItemEntity.setProduct(productEntityDomainMapper.mapToEntity(orderItem.getProduct()));
        orderItemEntity.setQuantity(orderItem.getQuantity());
        return orderItemEntity;
    }

    public List<OrderItem> mapToDomains(List<OrderItemEntity> orderItemEntities){
        return orderItemEntities.stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    public List<OrderItemEntity> mapToEntities(List<OrderItem> orderItems){
        return orderItems.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
