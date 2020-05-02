package edu.miu.waa.onlineshopping.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItem {
    private Integer orderItemId;
    private Integer quantity;
    private Double price;
    private Product product;

    private OrderItem(Integer orderItemId, Integer quantity, Double price, Product product) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    static public OrderItem of(Integer orderItemId, Integer quantity, Double price, Product product) {
        return new OrderItem(orderItemId, quantity, price, product);
    }
}
