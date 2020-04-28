package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.Role;
import edu.miu.waa.onlineshopping.domain.User;
import edu.miu.waa.onlineshopping.domain.UserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDomainRepository userDomainRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDomainRepository userDomainRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDomainRepository = userDomainRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUserName(String userName) {
        return userDomainRepository.findUserByUserName(userName);
    }

    public User save(User user){
        user.setActive(user.getRole()!= Role.SELLER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDomainRepository.save(user);
    }
}
