package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
}
