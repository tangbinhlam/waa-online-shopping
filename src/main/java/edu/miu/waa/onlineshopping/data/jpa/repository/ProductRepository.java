package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.SellerProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<SellerProductEntity, Integer> {

    @Query("select p from SellerProductEntity p where p.supplier.userId = ?1")
    List<SellerProductEntity> findAllBySupplier(int supplierId);

    @Query("select p from SellerProductEntity p WHERE p.productId IN (:ids)")
    List<SellerProductEntity> findProductsByIds(@Param("ids") List<Integer> ids);
}
