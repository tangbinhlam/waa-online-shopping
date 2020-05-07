package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate orderDate;

    @Column(name = "SHIPPED_DATE")
    private LocalDate shippedDate;

    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity owner;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity seller;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AddressEntity shipto;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    private double total;
}
