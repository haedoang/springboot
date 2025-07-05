package io.haedoang.apiserver.ui;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.haedoang.apiserver.application.RetryService;
import io.haedoang.apiserver.application.SampleRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleRestService service;
    private final RetryService retryService;
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

    @GetMapping("/bulkhead")
    @Bulkhead(name = "bulkheadApi", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<String> bulkheadApi() throws InterruptedException {
        Thread.sleep(1000);
        return ResponseEntity.ok("it works!");
    }

    @GetMapping("/rateLimiter")
    @RateLimiter(name = "rateLimiterApi", fallbackMethod = "fallback")
    public ResponseEntity<String> rateLimiterApi() {
        return ResponseEntity.ok("it works!");
    }

    @GetMapping("retry")
    public ResponseEntity<String> retry() {
        return ResponseEntity.ok(retryService.retryableService());
    }

    public ResponseEntity<String> fallback(Exception e) {
        log.warn("fallback", e);

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("fallback");
    }
}
