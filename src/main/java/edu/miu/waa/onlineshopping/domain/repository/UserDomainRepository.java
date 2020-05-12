package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.User;

import java.util.List;

public interface UserDomainRepository {
    User findUserByUserName(String userName);
    User save(User user);
    User approveSeller(Integer userId);
    List<User> findByActiveFalse();
    User addPoints(Integer userId, Double points);
    User minusPoints(Integer userId, Double points);
}
