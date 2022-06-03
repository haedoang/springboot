package io.haedoang.springsamplecaching.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fileName : CacheConfig
 * author : haedoang
 * date : 2022-06-03
 * description :
 */
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        //return new ConcurrentMapCacheManager("static", "cache", "config");
        return new ConcurrentMapCacheManager();
    }
}
