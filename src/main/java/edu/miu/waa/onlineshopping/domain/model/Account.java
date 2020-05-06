package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    private Integer accountId;

    @Valid
    private Address billingAddress;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull
    private LocalDate open;

    //TODO: add validate closed date should > open date
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull
    private LocalDate closed;

    @Min(value = 201, message = "{0} must > 200$")
    @NotNull
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
