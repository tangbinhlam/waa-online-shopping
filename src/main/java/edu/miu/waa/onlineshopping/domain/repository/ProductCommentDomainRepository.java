package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.ProductComment;

import java.util.List;

public interface ProductCommentDomainRepository {
    ProductComment save(ProductComment productComment);
    List<ProductComment> getNewProductComments();
    ProductComment approve(Integer productCommentId);
    ProductComment reject(Integer productCommentId);
}
