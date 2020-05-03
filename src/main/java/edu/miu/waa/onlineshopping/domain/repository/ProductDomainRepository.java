package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.Product;

import java.util.List;

public interface ProductDomainRepository {
    Product save(Product product);
    Product update(Product product);
    boolean delete(Integer productId);
    List<Product> findAllBySupplier(Integer supplierId);
    Product findProductByProductId(Integer productId);
    List<Product> findAll();
    List<Product> findProductsByIds(List<Integer> ids);
}
