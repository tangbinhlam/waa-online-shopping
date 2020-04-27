package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.User;
import edu.miu.waa.onlineshopping.domain.UserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDomainRepository userDomainRepository;

    @Autowired
    public UserService(UserDomainRepository userDomainRepository) {
        this.userDomainRepository = userDomainRepository;
    }

    public User findUserByUserName(String userName) {
        return userDomainRepository.findUserByUserName(userName);
    }

    public User save(User user){
        return userDomainRepository.save(user);
    }
}
