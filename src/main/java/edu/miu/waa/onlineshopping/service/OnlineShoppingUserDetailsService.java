package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.repository.UserDomainRepository;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import edu.miu.waa.onlineshopping.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OnlineShoppingUserDetailsService implements UserDetailsService {

    private final UserDomainRepository userDomainRepository;

    @Autowired
    public OnlineShoppingUserDetailsService(UserDomainRepository userDomainRepository) {
        this.userDomainRepository = userDomainRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        User user = userDomainRepository.findUserByUserName(userName);
        List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Role role) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.toString()));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                user.getActive(), true, true, true, authorities);
    }
}
