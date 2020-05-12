package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.entity.UserEntity;
import edu.miu.waa.onlineshopping.data.jpa.mapper.UserEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.UserRepository;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.repository.UserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public User approveSeller(Integer userId) {
        UserEntity user = userRepository.findById(userId).get();
        user.setActive(true);
        return userEntityDomainMapper.mapToDomain(userRepository.save(user));
    }

    @Override
    public List<User> findByActiveFalse() {
        return userRepository.findByActiveFalse().stream().map(userEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public User addPoints(Integer userId, Double points) {
        UserEntity user = userRepository.findById(userId).get();
        user.getAcount().setPoints(user.getAcount().getPoints() + points);
        return userEntityDomainMapper.mapToDomain(userRepository.save(user));
    }

    @Override
    public User minusPoints(Integer userId, Double points) {
        UserEntity user = userRepository.findById(userId).get();
        user.getAcount().setPoints(user.getAcount().getPoints() - points);
        return userEntityDomainMapper.mapToDomain(userRepository.save(user));
    }
}
