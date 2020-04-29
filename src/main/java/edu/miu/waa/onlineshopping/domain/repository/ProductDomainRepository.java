package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.Product;

import java.util.List;

public interface ProductDomainRepository {
    Product save(Product product);
    Product update(Product product);
    boolean delete(int productId);
    List<Product> findAllBySupplier(int supplierId);
    Product findProductByProductId(int productId);
}
