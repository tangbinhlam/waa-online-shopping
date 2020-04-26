package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.mapper.UserEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.UserRepository;
import edu.miu.waa.onlineshopping.domain.User;
import edu.miu.waa.onlineshopping.domain.UserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserDomainRepository {
    private final UserRepository userRepository;
    private final UserEntityDomainMapper userEntityDomainMapper;

    @Autowired
    public UserRepositoryAdapter(UserRepository userRepository, UserEntityDomainMapper userEntityDomainMapper) {
        this.userRepository = userRepository;
        this.userEntityDomainMapper = userEntityDomainMapper;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userEntityDomainMapper.mapToDomain(userRepository.findByUserName(userName));
    }

    @Override
    public User save(User user) {
        return userEntityDomainMapper.mapToDomain(userRepository.save(userEntityDomainMapper.mapToEntity(user)));
    }
}
