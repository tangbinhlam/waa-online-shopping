package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.model.ProductComment;
import edu.miu.waa.onlineshopping.domain.repository.ProductCommentDomainRepository;
import edu.miu.waa.onlineshopping.domain.repository.ProductDomainRepository;
import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    public List<ProductComment> getNewProductComments() {
        return productCommentDomainRepository.getNewProductComments();
    }

    public List<ProductComment> getApprovedProductComments(Integer productId) {
        return productCommentDomainRepository.getApprovedProductComments(productId);
    }

    @Transactional
    public ProductComment approve(Integer productCommentId) {
        return productCommentDomainRepository.approve(productCommentId);
    }

    @Transactional
    public ProductComment reject(Integer productCommentId) {
        return productCommentDomainRepository.reject(productCommentId);
    }

}
