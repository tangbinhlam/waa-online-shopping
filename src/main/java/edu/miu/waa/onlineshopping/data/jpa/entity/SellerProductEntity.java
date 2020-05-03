package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class SellerProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    private String producer;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "DESCRIPTION", columnDefinition="TEXT")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserEntity supplier;

}
