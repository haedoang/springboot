package io.haedoang.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class DbConfig {
    @Value("${combined}")
    private Boolean combined;

    @Bean
    public Boolean combined() {
        return combined;
    }
}
