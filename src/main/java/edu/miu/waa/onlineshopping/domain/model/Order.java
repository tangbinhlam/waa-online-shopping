package edu.miu.waa.onlineshopping.domain.model;

import edu.miu.waa.onlineshopping.domain.vo.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private LocalDate orderDate;
    private LocalDate shippedDate;
    private OrderStatus status;
    private User owner;
    private Address shipto;
    private List<OrderItem> orderItems = new ArrayList<>();
    private double total;

    private Order(Integer orderId, LocalDate orderDate, LocalDate shippedDate, OrderStatus status,
                  User owner, Address shipto, List<OrderItem> orderItems, double total) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.owner = owner;
        this.shipto = shipto;
        this.orderItems = orderItems;
        this.total = total;
    }

    public static Order of(Integer orderId, LocalDate orderDate, LocalDate shippedDate,
                           OrderStatus status, User owner, Address shipto,
                           List<OrderItem> orderItems, double total) {
        return new Order(orderId, orderDate, shippedDate,
                status, owner, shipto, orderItems, total);
    }
}
