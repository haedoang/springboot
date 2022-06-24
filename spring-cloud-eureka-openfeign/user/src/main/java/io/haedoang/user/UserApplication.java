package io.haedoang.user;

import io.haedoang.clients.team.TeamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * fileName : io.haedoang.user.UserApplication
 * author : haedoang
 * date : 2022-06-24
 * description :
 */
@Slf4j
@EnableFeignClients(basePackages = "io.haedoang.clients")
@EnableEurekaClient
@SpringBootApplication
@RestController
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RestTemplate template, TeamClient client) {
        return args -> {


//            String actual = template.getForObject("http://localhost:8081", String.class);
            String actual = client.index();
            log.info("get team : {}", actual);
        };
    }
}
