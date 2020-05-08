package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.SellerProductEntity;
import edu.miu.waa.onlineshopping.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityDomainMapper {

    private final UserEntityDomainMapper userEntityDomainMapper;

    @Autowired
    public ProductEntityDomainMapper(UserEntityDomainMapper userEntityDomainMapper) {
        this.userEntityDomainMapper = userEntityDomainMapper;
    }

    public Product mapToDomain(SellerProductEntity productEntity) {
        return (productEntity != null) ?
                Product.of(productEntity.getProductId(),
                        productEntity.getProductName(),
                        productEntity.getImagePath(),
                        productEntity.getDescription(),
                        userEntityDomainMapper.mapToDomain(productEntity.getSupplier()),
                        productEntity.getPrice(),
                        productEntity.getProducer()
                ) : null;
    }

    public SellerProductEntity mapToEntity(Product product) {
        SellerProductEntity productEntity = new SellerProductEntity();
        productEntity.setDescription(product.getDescription());
        productEntity.setImagePath(product.getImagePath());
        productEntity.setPrice(product.getPrice());
        productEntity.setSupplier(userEntityDomainMapper.mapToEntity(product.getSupplier()));
        productEntity.setProductName(product.getProductName());
        productEntity.setProductId(product.getProductId());
        productEntity.setProducer(product.getProducer());
        return productEntity;
    }
}
