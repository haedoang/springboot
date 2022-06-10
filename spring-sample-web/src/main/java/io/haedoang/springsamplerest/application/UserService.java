package io.haedoang.springsamplerest.application;

import io.haedoang.springsamplerest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * fileName : UserService
 * author : haedoang
 * date : 2022-06-10
 * description :
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private List<User> users;

    @PostConstruct
    private void init() {
        this.users = LongStream.iterate(1L, n -> n + 1).limit(10)
                .mapToObj((index) ->
                        User.valueOf(index, "user" + index, (int) index)
                ).collect(Collectors.toList());
    }

    public List<User> list() {
        return this.users;
    }

    public User save(User user) {
        user.setId(users.size() + 1L);
        this.users.add(user);
        return user;
    }
}
