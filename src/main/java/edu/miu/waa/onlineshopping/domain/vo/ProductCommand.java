package edu.miu.waa.onlineshopping.domain.vo;

import edu.miu.waa.onlineshopping.domain.model.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductCommand {
    private Integer productId;
    private String productName;
    private MultipartFile imagePath;
    private String description;
    private double price;
    private String producer;
    private String imageName;

    public ProductCommand(String productName, MultipartFile imagePath, String description, double price, String producer) {
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.price = price;
        this.producer = producer;
    }

    public static ProductCommand buillder(Product product) {
        ProductCommand productCommand = new ProductCommand();
        productCommand.setProductId(product.getProductId());
        productCommand.setDescription(product.getDescription());
        productCommand.setPrice(product.getPrice());
        productCommand.setProductName(product.getProductName());
        productCommand.setProducer(product.getProducer());
        productCommand.setImageName(product.getImagePath());
        return productCommand;
    }
}
