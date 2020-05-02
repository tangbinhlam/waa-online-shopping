package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.AddressEntity;
import edu.miu.waa.onlineshopping.domain.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityDomainMapper {
    public Address mapToDomain(AddressEntity addressEntity) {
        return (addressEntity != null) ? Address.of(addressEntity.getAddressId(),
                addressEntity.getAddressLine1(),
                addressEntity.getAddressLine2(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getPostcode(),
                addressEntity.getCountry()
        ) : null;
    }

    public AddressEntity mapToEntity(Address address) {
        AddressEntity AddressEntity = new AddressEntity();
        AddressEntity.setAddressId(address.getAddressId());
        AddressEntity.setAddressLine1(address.getAddressLine1());
        AddressEntity.setAddressLine2(address.getAddressLine2());
        AddressEntity.setCity(address.getCity());
        AddressEntity.setState(address.getState());
        AddressEntity.setPostcode(address.getPostcode());
        AddressEntity.setCountry(address.getCountry());
        return AddressEntity;
    }
}
