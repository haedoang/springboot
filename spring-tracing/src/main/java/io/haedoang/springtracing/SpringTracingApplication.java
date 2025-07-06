package io.haedoang.springtracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringTracingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTracingApplication.class, args);
    }

}
