package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.ProductCommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductCommentRepository extends CrudRepository<ProductCommentEntity, Integer> {
}
