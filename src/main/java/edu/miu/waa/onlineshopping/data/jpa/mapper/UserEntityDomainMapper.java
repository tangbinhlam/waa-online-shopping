package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.UserEntity;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityDomainMapper {

    private final AccountEntityDomainMapper accountEntityDomainMapper;

    @Autowired
    public UserEntityDomainMapper(AccountEntityDomainMapper accountEntityDomainMapper) {
        this.accountEntityDomainMapper = accountEntityDomainMapper;
    }

    public User mapToDomain(UserEntity userEntity) {
        return (userEntity != null)?User.of(userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getLastName(),
                userEntity.getActive(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                Role.of(userEntity.getRole()),
                userEntity.getAboutUs(),
                accountEntityDomainMapper.mapToDomain(userEntity.getAcount())
        ):null;
    }

    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setActive(user.getActive());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setRole(user.getRole().toString());
        userEntity.setAboutUs(user.getAboutUs());
        userEntity.setAcount(accountEntityDomainMapper.mapToEntity(user.getAccount()));
        return userEntity;
    }
}
