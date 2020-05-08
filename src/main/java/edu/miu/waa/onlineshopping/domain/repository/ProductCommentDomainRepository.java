package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.ProductComment;

public interface ProductCommentDomainRepository {
    ProductComment save(ProductComment productComment);
}
