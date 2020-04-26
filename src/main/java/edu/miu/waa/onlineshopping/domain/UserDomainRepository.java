package edu.miu.waa.onlineshopping.domain;

public interface UserDomainRepository {
    User findUserByUserName(String userName);
    User save(User user);
}
