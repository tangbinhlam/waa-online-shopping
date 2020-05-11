package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.entity.FollowerUserEntity;
import edu.miu.waa.onlineshopping.data.jpa.mapper.FollowerUserEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.FollowerUserRepository;
import edu.miu.waa.onlineshopping.data.jpa.repository.UserRepository;
import edu.miu.waa.onlineshopping.domain.model.FollowerUser;
import edu.miu.waa.onlineshopping.domain.repository.FollowerUserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FollowerUserRepositoryAdapter implements FollowerUserDomainRepository {

    private final FollowerUserRepository followerUserRepository;
    private final FollowerUserEntityDomainMapper followerUserEntityDomainMapper;
    private final UserRepository userRepository;

    @Autowired
    public FollowerUserRepositoryAdapter(FollowerUserRepository followerUserRepository, FollowerUserEntityDomainMapper followerUserEntityDomainMapper, UserRepository userRepository) {
        this.followerUserRepository = followerUserRepository;
        this.followerUserEntityDomainMapper = followerUserEntityDomainMapper;
        this.userRepository = userRepository;
    }

    @Override
    public FollowerUser follow(Integer followerId, Integer followedId) {
        FollowerUserEntity followerUserEntity = new FollowerUserEntity();
        followerUserEntity.setFollowUser(userRepository.findById(followerId).get());
        followerUserEntity.setFollowedUser(userRepository.findById(followedId).get());
        return followerUserEntityDomainMapper.mapToDomain(followerUserRepository.save(followerUserEntity));
    }

    @Override
    public boolean unFollow(FollowerUser followerUser) {
        followerUserRepository.deleteById(followerUser.getId());
        return true;
    }

    @Override
    public List<FollowerUser> getListFollowed(Integer userId) {
        return followerUserRepository.findByFollowedUserUserId(userId).stream().map(followerUserEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<FollowerUser> getListFollow(Integer userId) {
        return followerUserRepository.findByFollowUserUserId(userId).stream().map(followerUserEntityDomainMapper::mapToDomain).collect(Collectors.toList());
    }
}
