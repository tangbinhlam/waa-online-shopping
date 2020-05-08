package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.mapper.ProductCommentEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.ProductCommentRepository;
import edu.miu.waa.onlineshopping.domain.model.ProductComment;
import edu.miu.waa.onlineshopping.domain.repository.ProductCommentDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCommentRepositoryAdapter implements ProductCommentDomainRepository {

    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentEntityDomainMapper productCommentEntityDomainMapper;

    @Autowired
    public ProductCommentRepositoryAdapter(ProductCommentRepository productCommentRepository, ProductCommentEntityDomainMapper productCommentEntityDomainMapper) {
        this.productCommentRepository = productCommentRepository;
        this.productCommentEntityDomainMapper = productCommentEntityDomainMapper;
    }

    @Override
    public ProductComment save(ProductComment productComment) {
        return productCommentEntityDomainMapper.mapToDomain(productCommentRepository.save(productCommentEntityDomainMapper.mapToEntity(productComment)));
    }
}
