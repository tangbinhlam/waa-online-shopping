package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByUserName(String userName);
    List<UserEntity> findByActiveFalse();
}
