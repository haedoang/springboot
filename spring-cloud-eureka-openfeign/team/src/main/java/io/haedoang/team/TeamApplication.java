package io.haedoang.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName : TeamApplication
 * author : haedoang
 * date : 2022-06-24
 * description :
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
public class TeamApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeamApplication.class, args);
    }

    @GetMapping
    public String index() {
        return "team";
    }
}
