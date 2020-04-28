package edu.miu.waa.onlineshopping.domain;

import java.util.List;

public interface UserDomainRepository {
    User findUserByUserName(String userName);
    User save(User user);
    User approveSeller(Integer userId);
    List<User> findByActiveFalse();
}
