package io.haedoang.springehcache.user.application;

import io.haedoang.springehcache.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
            Arrays.asList(
                    new User("user1", 1),
                    new User("user2", 2),
                    new User("user3", 3),
                    new User("user4", 4),
                    new User("user5", 5),
                    new User("Tom", 30),
                    new User("Amy", 24)
            );

    public User getUser(String name) {
        log.info("UserService.getUser()");
        return users.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(new User("unknown", 0));
    }

    @Cacheable(
            value = "userCache",
            key = "#name",
            condition = "#name.length() > 4"
    )
    public User getCachedUser(String name) {
        log.info("UserService.getUserWithCache()");
        return users.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(new User("unknown", 0));
    }
}
