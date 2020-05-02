package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "ADDRESS_ID")
    private Integer accountId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private AddressEntity billingAddress;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity owner;

    @Column(name = "OPEN")
    private java.sql.Date open;

    @Column(name = "CLOSED")
    private java.sql.Date closed;

    private Double balance;
}
