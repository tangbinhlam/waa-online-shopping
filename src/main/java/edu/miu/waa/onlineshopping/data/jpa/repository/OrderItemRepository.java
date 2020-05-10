package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Integer> {
    List<OrderItemEntity> findOrderItemEntitiesByProductProductId(Integer productId);
}
