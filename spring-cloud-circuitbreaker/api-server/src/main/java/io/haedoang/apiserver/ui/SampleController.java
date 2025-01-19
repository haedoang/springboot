package io.haedoang.apiserver.ui;

import io.haedoang.apiserver.application.SampleRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleRestService service;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("/get")
    public Map get() {
        return service.get();
    }

    @GetMapping("/delay/{seconds}")
    public Map delay(@PathVariable int seconds) {

        return circuitBreakerFactory.create("delay").run(() -> service.delay(seconds), t -> {
            log.error("invoke fallback!", t);
            return Map.of("result", "fallback!");
        });
    }
}
