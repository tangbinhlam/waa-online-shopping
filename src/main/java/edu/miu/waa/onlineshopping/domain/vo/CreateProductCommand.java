package edu.miu.waa.onlineshopping.domain.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateProductCommand {
    private Integer productId;
    private String productName;
    private MultipartFile imagePath;
    private String description;
    private double price;
    private String producer;

    public CreateProductCommand(String productName, MultipartFile imagePath, String description, double price, String producer) {
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.price = price;
        this.producer = producer;
    }
}
