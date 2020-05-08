package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.OrderEntity;
import edu.miu.waa.onlineshopping.data.jpa.entity.SellerProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    @Query("select o from OrderEntity o where o.seller.userId = ?1")
    List<OrderEntity> findOrderBySeller(int sellerId);

    @Query("select o from OrderEntity o where o.owner.userId = ?1")
    List<OrderEntity> findOrderHistory(int buyerId);
}
