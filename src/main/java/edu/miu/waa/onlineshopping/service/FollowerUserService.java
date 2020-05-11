package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.FollowerUser;
import edu.miu.waa.onlineshopping.domain.repository.FollowerUserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowerUserService {

    private final FollowerUserDomainRepository followerUserDomainRepository;

    @Autowired
    public FollowerUserService(FollowerUserDomainRepository followerUserDomainRepository) {
        this.followerUserDomainRepository = followerUserDomainRepository;
    }

    public FollowerUser follow(Integer followerId, Integer followedId){
        return followerUserDomainRepository.follow(followerId, followedId);
    }

    public void unFollow(Integer followerId, Integer followedId){
        followerUserDomainRepository.unFollow(followerId, followedId);
    }

    public List<FollowerUser> findFollowersByUser(Integer followedId) {
        return followerUserDomainRepository.getListFollowed(followedId);
    }

    public List<FollowerUser> findFollowedByUser(Integer followId) {
        return followerUserDomainRepository.getListFollow(followId);
    }
 }
