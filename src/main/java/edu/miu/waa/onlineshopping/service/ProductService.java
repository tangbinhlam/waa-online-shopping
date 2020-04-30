package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.repository.ProductDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private ProductDomainRepository productDomainRepository;

    @Autowired
    public ProductService(ProductDomainRepository productDomainRepository) {
        this.productDomainRepository = productDomainRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllBySupplier(Integer supplierId) {
        return productDomainRepository.findAllBySupplier(supplierId);
    }

    @Transactional
    public Product save(Product product){
        return productDomainRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productDomainRepository.findAll();
    }

    @Transactional
    public boolean delete(Integer productId) {
        return productDomainRepository.delete(productId);
    }
}
