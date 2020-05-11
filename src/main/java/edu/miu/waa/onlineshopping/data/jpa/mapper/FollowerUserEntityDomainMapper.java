package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.FollowerUserEntity;
import edu.miu.waa.onlineshopping.domain.model.FollowerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowerUserEntityDomainMapper {

    private final UserEntityDomainMapper userEntityDomainMapper;

    @Autowired
    public FollowerUserEntityDomainMapper(UserEntityDomainMapper userEntityDomainMapper) {
        this.userEntityDomainMapper = userEntityDomainMapper;
    }

    public FollowerUser mapToDomain(FollowerUserEntity followerUserEntity) {
        return (followerUserEntity != null) ?
                FollowerUser.of(followerUserEntity.getId(),
                        userEntityDomainMapper.mapToDomain(followerUserEntity.getFollowUser()),
                        userEntityDomainMapper.mapToDomain(followerUserEntity.getFollowedUser())
                ) : null;
    }

    public FollowerUserEntity mapToEntity(FollowerUser followerUser) {
        FollowerUserEntity FollowerUserEntity = new FollowerUserEntity();
        FollowerUserEntity.setFollowedUser(userEntityDomainMapper.mapToEntity(followerUser.getFollowedUser()));
        FollowerUserEntity.setFollowUser(userEntityDomainMapper.mapToEntity(followerUser.getFollowUser()));
        FollowerUserEntity.setId(followerUser.getId());
        return FollowerUserEntity;
    }
}
