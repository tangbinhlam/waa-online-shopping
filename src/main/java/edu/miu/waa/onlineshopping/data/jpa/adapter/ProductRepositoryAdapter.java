package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.entity.SellerProductEntity;
import edu.miu.waa.onlineshopping.data.jpa.mapper.ProductEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.ProductRespository;
import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.repository.ProductDomainRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ProductRepositoryAdapter implements ProductDomainRepository {
    private final ProductRespository productRespository;
    private final ProductEntityDomainMapper productEntityDomainMapper;

    public ProductRepositoryAdapter(ProductRespository productRespository, ProductEntityDomainMapper productEntityDomainMapper) {
        this.productRespository = productRespository;
        this.productEntityDomainMapper = productEntityDomainMapper;
    }

    @Override
    public Product save(Product product) {
        return productEntityDomainMapper.mapToDomain(productRespository.save(productEntityDomainMapper.mapToEntity(product)));
    }

    @Override
    public Product update(Product product) {
        SellerProductEntity productEntity = productRespository.findById(product.getProductId()).get();
        productEntity.setProductName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        productEntity.setImagePath(product.getImagePath());
        productEntity.setDescription(product.getDescription());
        return productEntityDomainMapper.mapToDomain(productRespository.save(productEntity));
    }

    @Override
    public boolean delete(Integer productId) {
        try {
            SellerProductEntity productEntity = productRespository.findById(productId).get();
            productRespository.delete(productEntity);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<Product> findAllBySupplier(Integer supplierId) {
        return productRespository.findAllBySupplier(supplierId).stream().map(productEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Product findProductByProductId(Integer productId) {
        return productEntityDomainMapper.mapToDomain(productRespository.findById(productId).orElse(null));
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRespository.findAll().spliterator(), false).map(productEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }
}
