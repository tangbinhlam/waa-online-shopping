package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {
    private Integer paymentId;
    private LocalDate paid;
    private double total;
    private String details;
    private Order order;
    private Account payAccount;
    private Account receiveAccount;

    public Payment(Integer paymentId, LocalDate paid, double total, String details, Order order, Account payAccount, Account receiveAccount) {
        this.paymentId = paymentId;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.order = order;
        this.payAccount = payAccount;
        this.receiveAccount = receiveAccount;
    }

    public static Payment of(Integer paymentId, LocalDate paid, double total, String details, Order order, Account payAccount, Account receiveAccount) {
        return new Payment(paymentId, paid, total, details, order, payAccount, receiveAccount);
    }
}
