package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.FollowerUserEntity;
import edu.miu.waa.onlineshopping.data.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowerUserRepository extends CrudRepository<FollowerUserEntity, Integer> {
    List<FollowerUserEntity> findByFollowedUserUserId(Integer followedId);

    List<FollowerUserEntity> findByFollowUserUserId(Integer follow);

    FollowerUserEntity findByFollowUserUserIdAndFollowedUserUserId(Integer followUser_userId, Integer followedUser_userId);
}
