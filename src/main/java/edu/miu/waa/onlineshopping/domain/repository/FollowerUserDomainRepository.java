package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.FollowerUser;

import java.util.List;

public interface FollowerUserDomainRepository {
    FollowerUser follow(Integer followerId, Integer followedId);

    boolean unFollow(FollowerUser followerUser);

    List<FollowerUser> getListFollowed(Integer userId);

    List<FollowerUser> getListFollow(Integer userId);
}
