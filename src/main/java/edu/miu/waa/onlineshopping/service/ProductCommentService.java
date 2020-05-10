package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.model.ProductComment;
import edu.miu.waa.onlineshopping.domain.repository.ProductCommentDomainRepository;
import edu.miu.waa.onlineshopping.domain.repository.ProductDomainRepository;
import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProductCommentService {

    private final ProductDomainRepository productDomainRepository;
    private final ProductCommentDomainRepository productCommentDomainRepository;


    public ProductCommentService(ProductDomainRepository productDomainRepository, ProductCommentDomainRepository productCommentDomainRepository) {
        this.productDomainRepository = productDomainRepository;
        this.productCommentDomainRepository = productCommentDomainRepository;
    }

    @Transactional
    public ProductComment saveComment(Integer productId, ProductComment comment) {
        Product product = productDomainRepository.findProductByProductId(productId);
        comment.setProduct(product);
        comment.setReviewDate(LocalDate.now());
        comment.setCommentStatus(CommentStatus.ADDED);
        return productCommentDomainRepository.save(comment);
    }
}
