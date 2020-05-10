package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.entity.SellerProductEntity;
import edu.miu.waa.onlineshopping.data.jpa.mapper.ProductEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.ProductRepository;
import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.repository.ProductDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ProductRepositoryAdapter implements ProductDomainRepository {
    private final ProductRepository productRepository;
    private final ProductEntityDomainMapper productEntityDomainMapper;

    @Autowired
    public ProductRepositoryAdapter(ProductRepository productRepository, ProductEntityDomainMapper productEntityDomainMapper) {
        this.productRepository = productRepository;
        this.productEntityDomainMapper = productEntityDomainMapper;
    }

    @Override
    public Product save(Product product) {
        return productEntityDomainMapper.mapToDomain(productRepository.save(productEntityDomainMapper.mapToEntity(product)));
    }

    @Override
    public Product update(Product product) {
        SellerProductEntity productEntity = productRepository.findById(product.getProductId()).get();
        productEntity.setProductName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        productEntity.setImagePath(product.getImagePath());
        productEntity.setDescription(product.getDescription());
        productEntity.setProducer(product.getProducer());
        return productEntityDomainMapper.mapToDomain(productRepository.save(productEntity));
    }

    @Override
    public boolean delete(Integer productId) {
        try {
            SellerProductEntity productEntity = productRepository.findById(productId).get();
            productRepository.delete(productEntity);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<Product> findAllBySupplier(Integer supplierId) {
        return productRepository.findAllBySupplier(supplierId).stream().map(productEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Product findProductByProductId(Integer productId) {
        return productEntityDomainMapper.mapToDomain(productRepository.findById(productId).orElse(null));
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).map(productEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByIds(List<Integer> ids) {
        return productRepository.findProductsByIds(ids).stream().map(productEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }
}
