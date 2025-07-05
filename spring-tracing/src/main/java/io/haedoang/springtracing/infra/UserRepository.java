package io.haedoang.springtracing.infra;

import brave.Tracer;
import io.haedoang.springtracing.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final Tracer tracer;
    private final RestClient userClient;

    public List<User> getUsers() {
        log.info("invoke userRepository.gerUsers {} {}", tracer.currentSpan().context().traceId(), tracer.currentSpan().context().spanId());


        return userClient.get().uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
