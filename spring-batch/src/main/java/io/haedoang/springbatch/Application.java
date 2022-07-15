package io.haedoang.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        System.exit(SpringApplication.exit(SpringApplication.run(Application.class, args)));

    }

}
