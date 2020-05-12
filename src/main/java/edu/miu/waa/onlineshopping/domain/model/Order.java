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
    private LocalDate deliveredDate;
    private OrderStatus status;
    private User owner;
    private User seller;
    private Address shipto;
    private List<OrderItem> orderItems = new ArrayList<>();
    private double total;
    private double pointUsed;
    private double pointEarned;

    private Order(Integer orderId, LocalDate orderDate, LocalDate shippedDate, LocalDate deliveredDate, OrderStatus status,
                  User owner, User seller, Address shipto, List<OrderItem> orderItems, double total, double pointUsed, double pointEarned) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.deliveredDate = deliveredDate;
        this.status = status;
        this.owner = owner;
        this.seller = seller;
        this.shipto = shipto;
        this.orderItems = orderItems;
        this.total = total;
        this.pointUsed = pointUsed;
        this.pointEarned = pointEarned;
    }

    public static Order of(Integer orderId, LocalDate orderDate, LocalDate shippedDate, LocalDate deliveredDate,
                           OrderStatus status, User owner, User seller, Address shipto,
                           List<OrderItem> orderItems, double total, double pointUsed, double pointEarned) {
        return new Order(orderId, orderDate, shippedDate, deliveredDate,
                status, owner, seller, shipto, orderItems, total, pointUsed, pointEarned);
    }
}
