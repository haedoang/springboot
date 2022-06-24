package io.haedoang.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * fileName : WebConfig
 * author : haedoang
 * date : 2022-06-24
 * description :
 */
@Configuration
public class WebConfig {

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
