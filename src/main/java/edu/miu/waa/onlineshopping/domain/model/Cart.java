package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cart {
    private String user;
    private List<CardItem> cardItems = new ArrayList<>();

    public Cart(String user, List<CardItem> cardItems) {
        this.user = user;
        this.cardItems = cardItems;
    }

    public void addItemToCart(Integer productId, Integer quantity) {
        if (cardItems.stream().anyMatch(cardItem -> cardItem.getProductId().equals(productId))) {
            cardItems = cardItems.stream().map(cardItem -> {
                if (cardItem.getProductId().equals(productId)) {
                    cardItem.setQuantity(cardItem.getQuantity() + quantity);
                }
                return cardItem;
            }).collect(Collectors.toList());
        } else {
            cardItems.add(new CardItem(quantity, productId));
        }
    }

    public void removeItemFormCart(Integer productId) {
        cardItems = cardItems.stream().filter(cardItem -> !cardItem.getProductId().equals(productId)).collect(Collectors.toList());
    }
}
