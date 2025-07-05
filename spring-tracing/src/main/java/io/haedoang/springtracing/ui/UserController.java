package io.haedoang.springtracing.ui;

import brave.Tracer;
import io.haedoang.springtracing.application.UserService;
import io.haedoang.springtracing.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final Tracer tracer;
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        log.info("invoke userController.gerUsers {} {}", tracer.currentSpan().context().traceId(), tracer.currentSpan().context().spanId());

        return userService.getUsers();
    }
}
