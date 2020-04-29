package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Product {
    private Integer productId;
    private String productName;
    private String imagePath;
    private String description;
    private double price;
    private User supplier;

    private Product(Integer productId, String productName, String imagePath, String description, User supplier, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
    }

    public static Product of(Integer productId, String productName, String imagePath, String description, User supplier, Double price) {
        return new Product(productId, productName, imagePath, description, supplier, price);
    }
}
