package io.haedoang.springdatamongodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022-08-24
 * description : <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.nosql.mongodb.embedded">embedded Mongo</a><br/>
 * Embedded Mongo 사용 시 버전 정보를 명시해야 한다 <br/>
 * 최초 1회 다운로드 후 이후에는 다운로드하지 않고 동작한다 <br/>
 * spring.mongodb.embedded.version
 */

@DataMongoTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("USER 등록하기")
    public void insertUser() {
        // given
        userRepository.insert(new User("user1", 30));
        userRepository.insert(new User("user2", 32));

        // when
        List<User> actual = userRepository.findAll();

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("USER 조회하기")
    public void searchUser() {
        // given
        userRepository.insert(new User("user1", 30));
        userRepository.insert(new User("user2", 32));

        // when
        List<User> actual = userRepository.findAll();

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("USER 수정하기")
    public void update() {
        // given
        User savedUser = userRepository.insert(new User("haedoang", 34));

        // when
        User findUser = userRepository.findById(savedUser.getId())
                .orElseThrow(IllegalStateException::new);
        findUser.setName("updatedName");
        userRepository.save(findUser);

        // then
        assertThat(userRepository.findById(findUser.getId()).get().getName())
                .isEqualTo("updatedName");
    }

    @Test
    @DisplayName("USER 삭제하기")
    public void delete() {
        // given
        User savedUser = userRepository.insert(new User("haedoang", 34));

        // when
        userRepository.deleteById(savedUser.getId());

        // then
        assertThat(userRepository.findAll()).hasSize(0);
    }



}
