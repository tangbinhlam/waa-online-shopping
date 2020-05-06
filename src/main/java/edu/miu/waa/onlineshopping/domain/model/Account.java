package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    private Integer accountId;
    private Address billingAddress;
    private LocalDate open;
    private LocalDate closed;
    private double balance;

    private Account(Integer accountId, double balance, Address billingAddress, LocalDate open, LocalDate closed) {
        this.accountId = accountId;
        this.billingAddress = billingAddress;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
    }

    public static Account of(Integer accountId, double balance, Address billingAddress, LocalDate open, LocalDate closed) {
        return new Account(accountId, balance, billingAddress, open, closed);
    }
}
//TODO: Put validate && Should not have setter all data process by construct then we can
// validate all in one and can easy check validate relate and throw right Business Exception like
// Close date < open date
