package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    private Integer addressId;

    @NotEmpty
    private String addressLine1;
    private String addressLine2;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String postcode;

    @NotEmpty
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
