package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.UserEntity;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void add_user_saved_successfull() {
        // Given
        UserEntity newUser = new UserEntity("xxx", "xxx", "xxx@gmail.com", "0973859577", "ADMIN", "Lam", "Tang","About us");
        // When
        userRepository.save(newUser);
        UserEntity userEntitie = userRepository.findByUserName("xxx");
        // Then
        assertThat(userEntitie).isNotNull();
        assertThat(userEntitie.getActive()).isEqualTo(newUser.getActive());
        assertThat(userEntitie.getPassword()).isEqualTo(newUser.getPassword());
        assertThat(userEntitie.getEmail()).isEqualTo(newUser.getEmail());
    }
}
