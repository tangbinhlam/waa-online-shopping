package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.parameters.P;

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

    public void addItemToCart(Integer productId) {
        if(cardItems.stream().anyMatch( cardItem -> cardItem.getProductId().equals(productId))){
            cardItems = cardItems.stream().map( cardItem -> {
                if(cardItem.getProductId().equals(productId)){
                    cardItem.setQuantity(cardItem.getQuantity() + 1);
                }
                return cardItem;
            }).collect(Collectors.toList());
        }else {
            cardItems.add(new CardItem(1, productId));
        }
    }
}
