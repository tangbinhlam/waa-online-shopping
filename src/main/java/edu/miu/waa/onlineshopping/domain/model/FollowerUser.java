package edu.miu.waa.onlineshopping.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FollowerUser {
    private Integer id;
    private User followUser;
    private User followedUser;

    private FollowerUser(Integer id, User followUser, User followedUser) {
        this.id = id;
        this.followUser = followUser;
        this.followedUser = followedUser;
    }

    public static FollowerUser of(Integer id, User followUser, User followedUser) {
        return new FollowerUser(id, followUser, followedUser);
    }
}
