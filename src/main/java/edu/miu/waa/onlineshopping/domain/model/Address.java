package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    private Integer addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postcode;
    private String country;

    private Address(Integer addressId, String addressLine1, String addressLine2,
                    String city, String state, String postcode, String country) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }

    public static Address of(Integer addressId, String addressLine1, String addressLine2,
                             String city, String state, String postcode, String country) {
        return new Address(addressId, addressLine1, addressLine2,
                city, state, postcode, country);
    }
}
