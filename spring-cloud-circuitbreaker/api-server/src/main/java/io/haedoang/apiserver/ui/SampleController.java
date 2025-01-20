package io.haedoang.apiserver.ui;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.haedoang.apiserver.application.SampleRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleRestService service;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @GetMapping("/get")
    public Map get() {
        return service.get();
    }

    @GetMapping("/delay/{seconds}")
    public Map delay(@PathVariable int seconds) {

        return circuitBreakerFactory.create("delay").run(() -> service.delay(seconds), t -> {
            log.error("invoke delay fallback!", t);
            return Map.of("result", "fallback!");
        });
    }


    @GetMapping("/delay2/{seconds}")
    public Map delay2(@PathVariable int seconds) {

        return circuitBreakerFactory.create("slow").run(() -> service.delay(seconds), t -> {
            log.error("invoke delay2 fallback!", t);

            return Map.of("result", "fallback!", "status", circuitBreakerRegistry.circuitBreaker("slow").getState());
        });
    }

    @GetMapping("/circuit/{name}")
    public CircuitBreaker.State circuitStatus(@PathVariable String name) {

        return circuitBreakerRegistry.circuitBreaker(name).getState();
    }
}
