package io.haedoang.springredis.user.application;

import io.haedoang.springredis.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * fileName : MyService
 * author : haedoang
 * date : 2022-06-03
 * description :
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final List<User> users =
            new ArrayList<>(
                    Arrays.asList(
                            new User("user1", 1),
                            new User("user2", 2),
                            new User("user3", 3),
                            new User("user4", 4),
                            new User("user5", 5)
                    )
            );

    public User getUser(String name) {
        log.info("UserService.getUser()");
        return users.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(new User("unknown", 0));
    }

    @Cacheable("user")
    public User getCachedUser(String name) {
        log.info("UserService.getUserWithCache()");
        return users.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(new User("unknown", 0));
    }
    public void updateUser(String name, User user) {
        log.info("UserService.updateUser()");
        boolean removed = users.removeIf(it -> it.getName().equals(name));
        if (removed) {
            users.add(user);
        }
    }

    @CacheEvict(value = "user", key="#name")
    public void deleteUser(String name) {
        log.info("UserService.deleteUser()");
        users.removeIf(it -> it.getName().equals(name));
    }

    public List<User> getAll() {
        return users;
    }
}
