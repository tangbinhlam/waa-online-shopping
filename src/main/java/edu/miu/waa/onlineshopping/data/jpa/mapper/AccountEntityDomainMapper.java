package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.AccountEntity;
import edu.miu.waa.onlineshopping.domain.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityDomainMapper {

    private AddressEntityDomainMapper addressEntityDomainMapper;

    @Autowired
    public AccountEntityDomainMapper(AddressEntityDomainMapper addressEntityDomainMapper) {
        this.addressEntityDomainMapper = addressEntityDomainMapper;
    }

    public Account mapToDomain(AccountEntity accountEntity) {
        return (accountEntity != null) ?
                Account.of(accountEntity.getAccountId(),
                        accountEntity.getPoints(),
                        addressEntityDomainMapper.mapToDomain(accountEntity.getBillingAddress()),
                        accountEntity.getOpen(),
                        accountEntity.getClosed()
                ) : null;
    }

    public AccountEntity mapToEntity(Account account) {
        if(account == null) {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(account.getAccountId());
        accountEntity.setBillingAddress(addressEntityDomainMapper.mapToEntity(account.getBillingAddress()));
        accountEntity.setOpen(account.getOpen());
        accountEntity.setClosed(account.getClosed());
        accountEntity.setPoints(account.getPoints());
        return accountEntity;
    }
}
