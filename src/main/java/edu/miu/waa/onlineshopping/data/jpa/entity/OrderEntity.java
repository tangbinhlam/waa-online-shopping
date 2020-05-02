package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_ENTITY")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "ORDERED_DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private java.sql.Date orderDate;

    @Column(name = "SHIPPED_DATE")
    private java.sql.Date shippedDate;

    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity owner;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private AddressEntity shipto;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    private double total;
}
