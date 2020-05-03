package edu.miu.waa.onlineshopping.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardItem {
    private Integer quantity;
    private Integer productId;
    private Product product;

    public CardItem(Integer quantity, Integer productId) {
        this.quantity = quantity;
        this.productId = productId;
    }
}
