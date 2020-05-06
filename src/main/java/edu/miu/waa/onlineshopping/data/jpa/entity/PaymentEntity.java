package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PAYMENT")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_ID")
    private Integer paymentId;

    @Column(name = "PAID", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate paid;

    private double total;

    private String details;

    @OneToOne(fetch = FetchType.EAGER)
    private OrderEntity order;

    @OneToOne(fetch = FetchType.EAGER)
    private AccountEntity payAccount;

    @OneToOne(fetch = FetchType.EAGER)
    private AccountEntity ReceiveAccount;
}
