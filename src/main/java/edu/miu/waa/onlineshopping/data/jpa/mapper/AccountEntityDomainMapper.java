package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.AccountEntity;
import edu.miu.waa.onlineshopping.domain.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityDomainMapper {

    private UserEntityDomainMapper userEntityDomainMapper;
    private AddressEntityDomainMapper addressEntityDomainMapper;

    @Autowired
    public AccountEntityDomainMapper(UserEntityDomainMapper userEntityDomainMapper, AddressEntityDomainMapper addressEntityDomainMapper) {
        this.userEntityDomainMapper = userEntityDomainMapper;
        this.addressEntityDomainMapper = addressEntityDomainMapper;
    }

    public Account mapToDomain(AccountEntity accountEntity) {
        return (accountEntity != null) ?
                Account.of(accountEntity.getAccountId(),
                        accountEntity.getBalance(),
                        addressEntityDomainMapper.mapToDomain(accountEntity.getBillingAddress()),
                        userEntityDomainMapper.mapToDomain(accountEntity.getOwner()),
                        accountEntity.getOpen(),
                        accountEntity.getClosed()
                ) : null;
    }

    public AccountEntity mapToEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(account.getAccountId());
        accountEntity.setBillingAddress(addressEntityDomainMapper.mapToEntity(account.getBillingAddress()));
        accountEntity.setOwner(userEntityDomainMapper.mapToEntity(account.getOwner()));
        accountEntity.setOpen(account.getOpen());
        accountEntity.setClosed(account.getClosed());
        accountEntity.setBalance(account.getBalance());
        return accountEntity;
    }
}
