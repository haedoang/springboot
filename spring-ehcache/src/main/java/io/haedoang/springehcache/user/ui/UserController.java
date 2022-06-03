package io.haedoang.springehcache.user.ui;

import io.haedoang.springehcache.user.application.UserService;
import io.haedoang.springehcache.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("{name}")
    public User getUser(@PathVariable(name = "name") String name) {
        log.info("api/v1/users/{} invoked", name );
        return userService.getUser(name);
    }

    @GetMapping("{name}/cached")
    public User getCachedUser(@PathVariable(name = "name") String name) {
        log.info("api/v1/users/{}/cached invoked", name );
        log.info("cacheMap: {}", cacheManager.getCacheNames());
        return userService.getCachedUser(name);
    }
}
