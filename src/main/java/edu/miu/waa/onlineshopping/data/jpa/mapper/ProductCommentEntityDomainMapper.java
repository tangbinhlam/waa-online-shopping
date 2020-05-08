package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.ProductCommentEntity;
import edu.miu.waa.onlineshopping.domain.model.ProductComment;
import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCommentEntityDomainMapper {

    private final UserEntityDomainMapper userEntityDomainMapper;
    private final ProductEntityDomainMapper productEntityDomainMapper;

    @Autowired
    public ProductCommentEntityDomainMapper(UserEntityDomainMapper userEntityDomainMapper, ProductEntityDomainMapper productEntityDomainMapper) {
        this.userEntityDomainMapper = userEntityDomainMapper;
        this.productEntityDomainMapper = productEntityDomainMapper;
    }

    public ProductComment mapToDomain(ProductCommentEntity productCommentEntity) {
        if(productCommentEntity == null){
            return null;
        }else {
            ProductComment productComment = ProductComment.of(productCommentEntity.getCommentId(),
                    productCommentEntity.getComment(),
                    productCommentEntity.getRating(),
                    userEntityDomainMapper.mapToDomain(productCommentEntity.getReviewUser()),
                    productCommentEntity.getReviewDate(),
                    CommentStatus.valueOf(productCommentEntity.getStatus()));
            productComment.setProduct(productEntityDomainMapper.mapToDomain(productCommentEntity.getProduct()));
            return productComment;
        }
    }

    public ProductCommentEntity mapToEntity(ProductComment productComment) {
        if(productComment == null) {
            return null;
        }
        ProductCommentEntity productCommentEntity = new ProductCommentEntity();
        productCommentEntity.setCommentId(productComment.getCommentId());
        productCommentEntity.setReviewUser(userEntityDomainMapper.mapToEntity(productComment.getReviewUser()));
        productCommentEntity.setComment(productComment.getComment());
        productCommentEntity.setRating(productComment.getRating());
        productCommentEntity.setReviewDate(productComment.getReviewDate());
        productCommentEntity.setStatus(productComment.getCommentStatus().toString());
        productCommentEntity.setProduct(productEntityDomainMapper.mapToEntity(productComment.getProduct()));
        return productCommentEntity;
    }

    public List<ProductComment> mapToDomains(List<ProductCommentEntity> productCommentEntities){
        return productCommentEntities==null?null:productCommentEntities.stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    public List<ProductCommentEntity> mapToEntities(List<ProductComment> productComments){
        return productComments==null?null:productComments.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
