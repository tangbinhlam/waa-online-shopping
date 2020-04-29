package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.SellerProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRespository extends CrudRepository<SellerProductEntity, Integer> {

    @Query("select p from SellerProductEntity p where p.supplier.userId = ?1")
    List<SellerProductEntity> findAllBySupplier(int supplierId);
}
