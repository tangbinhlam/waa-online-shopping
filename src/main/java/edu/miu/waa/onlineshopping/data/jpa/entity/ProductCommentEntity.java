package edu.miu.waa.onlineshopping.data.jpa.entity;

import edu.miu.waa.onlineshopping.domain.model.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_COMMENT")
public class ProductCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Integer commentId;

    @Column(name = "COMMENT", columnDefinition = "TEXT")
    private String comment;

    private Integer rating;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity reviewUser;

    @Column(name = "REVIEW_DATE")
    private LocalDate reviewDate;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private SellerProductEntity product;
}
