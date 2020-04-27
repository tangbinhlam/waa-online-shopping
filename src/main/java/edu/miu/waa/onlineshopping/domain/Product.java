package edu.miu.waa.onlineshopping.domain;

import lombok.Getter;

@Getter
public class Product {
    private Integer productId;
    private String productName;
    private String imagePath;
    private String description;
    private User supplier;

    private Product(Integer productId, String productName, String imagePath, String description, User supplier) {
        this.productId = productId;
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.supplier = supplier;
    }

    public static Product of(Integer productId, String productName, String imagePath, String description, User supplier) {
        return new Product(productId, productName, imagePath, description, supplier);
    }
}
