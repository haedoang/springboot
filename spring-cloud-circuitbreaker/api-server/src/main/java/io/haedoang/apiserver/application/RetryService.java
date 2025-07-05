package io.haedoang.apiserver.application;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RetryService {

    private AtomicInteger counter = new AtomicInteger();

    @Retry(name = "retryService")
    public String retryableService() {
        counter.incrementAndGet();
        if (counter.get() <= 2) {
            throw new RuntimeException("Intentional failure");
        }

        return "Success after " + counter + " attempts";
    }
}
