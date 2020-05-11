package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.entity.ProductCommentEntity;
import edu.miu.waa.onlineshopping.data.jpa.mapper.ProductCommentEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.ProductCommentRepository;
import edu.miu.waa.onlineshopping.domain.model.ProductComment;
import edu.miu.waa.onlineshopping.domain.repository.ProductCommentDomainRepository;
import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<ProductComment> getNewProductComments() {
        return  productCommentEntityDomainMapper.mapToDomains(productCommentRepository.findProductCommentEntitiesByStatusEquals(CommentStatus.ADDED.toString()));
    }

    @Override
    public ProductComment approve(Integer productCommentId) {
        ProductCommentEntity productCommentEntity = productCommentRepository.findById(productCommentId).get();
        productCommentEntity.setStatus(CommentStatus.APPROVED.toString());
        return productCommentEntityDomainMapper.mapToDomain(productCommentRepository.save(productCommentEntity));
    }

    @Override
    public ProductComment reject(Integer productCommentId) {
        ProductCommentEntity productCommentEntity = productCommentRepository.findById(productCommentId).get();
        productCommentEntity.setStatus(CommentStatus.REJECT.toString());
        return productCommentEntityDomainMapper.mapToDomain(productCommentRepository.save(productCommentEntity));
    }

    @Override
    public List<ProductComment> getApprovedProductComments(Integer productId) {
        return  productCommentEntityDomainMapper.mapToDomains(productCommentRepository.findProductCommentEntitiesByStatusEqualsAndProductProductId(CommentStatus.APPROVED.toString(), productId));
    }
}
