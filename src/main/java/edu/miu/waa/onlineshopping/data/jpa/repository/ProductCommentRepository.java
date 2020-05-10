package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.ProductCommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCommentRepository extends CrudRepository<ProductCommentEntity, Integer> {
    List<ProductCommentEntity> findProductCommentEntitiesByStatusEquals(String status);
}
