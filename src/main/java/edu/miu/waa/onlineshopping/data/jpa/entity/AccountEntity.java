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
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private AddressEntity billingAddress;

    @Column(name = "OPEN")
    private LocalDate open;

    @Column(name = "CLOSED")
    private LocalDate closed;

    private Double points;
}
