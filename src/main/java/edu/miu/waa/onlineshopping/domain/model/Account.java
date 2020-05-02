package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    private Integer accountId;
    private Address billingAddress;
    private User owner;
    private java.sql.Date open;
    private java.sql.Date closed;
    private double balance;

    private Account(Integer accountId, double balance, Address billingAddress,
                    User owner, Date open, Date closed) {
        this.accountId = accountId;
        this.billingAddress = billingAddress;
        this.owner = owner;
        this.open = open;
        this.closed = closed;
    }

    public static Account of(Integer accountId, double balance, Address billingAddress,
                             User owner, Date open, Date closed) {
        return new Account(accountId, balance, billingAddress, owner, open, closed);
    }
}
//TODO: Put validate && Should not have setter all data process by construct then we can
// validate all in one and can easy check validate relate and throw right Business Exception like
// Close date < open date
