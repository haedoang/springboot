package io.haedoang.springredis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;


/**
 * fileName : CacheManagerCheck
 * author : haedoang
 * date : 2022-06-03
 * description :
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheManagerCheck implements CommandLineRunner {
    private final CacheManager cacheManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("---------------------------------------------");
        log.info("using cache manager: {}", this.cacheManager.getClass().getName());
        log.info("---------------------------------------------");
    }
}
