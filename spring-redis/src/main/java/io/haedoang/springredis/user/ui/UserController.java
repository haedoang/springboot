package io.haedoang.springredis.user.ui;

import io.haedoang.springredis.user.application.UserService;
import io.haedoang.springredis.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * fileName : UserController
 * author : haedoang
 * date : 2022-06-03
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final CacheManager cacheManager;

    @GetMapping
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping("{name}")
    public User getUser(@PathVariable(name = "name") String name) {
        log.info("api/v1/users/{} invoked", name);
        return userService.getUser(name);
    }

    @GetMapping("{name}/cached")
    public User getCachedUser(@PathVariable(name = "name") String name) {
        log.info("api/v1/users/{}/cached invoked", name);
        log.info("cacheMap: {}", cacheManager.getCacheNames());
        return userService.getCachedUser(name);
    }

    @DeleteMapping("{name}")
    public void deleteUser(@PathVariable(name = "name") String name) {
        log.info("api/v1/users/{}, {} invoked", name);
        userService.deleteUser(name);
    }

    @PutMapping("{name}")
    public void patchUser(@PathVariable(name = "name") String name, @RequestBody User user) {
        log.info("api/v1/users/{}, {} invoked", name, user);
        userService.updateUser(name, user);
    }

}
