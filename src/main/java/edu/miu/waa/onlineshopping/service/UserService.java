package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.repository.UserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private UserDomainRepository userDomainRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDomainRepository userDomainRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDomainRepository = userDomainRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    public User findUserByUserName(String userName) {
        return userDomainRepository.findUserByUserName(userName);
    }

    @Transactional
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDomainRepository.save(user);
    }

    @Transactional
    public User approveSeller(Integer userId) {
        return userDomainRepository.approveSeller(userId);
    }

    @Transactional(readOnly = true)
    public List<User> findByActiveFalse() {
        return userDomainRepository.findByActiveFalse();
    }
}
