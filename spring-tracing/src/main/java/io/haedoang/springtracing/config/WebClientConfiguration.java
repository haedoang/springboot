package io.haedoang.springtracing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public RestClient userClient(RestClient.Builder builder) {
        return builder.baseUrl("https://jsonplaceholder.typicode.com/")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    // spanId 변경되지 않음(spring 주입 방식이 아니기때문에)
    @Bean
    public RestClient userClient2() {
        return RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
