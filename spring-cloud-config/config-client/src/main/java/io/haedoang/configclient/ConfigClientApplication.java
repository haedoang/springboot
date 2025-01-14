package io.haedoang.configclient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class ConfigClientApplication {

    // foo-development-db.properties
    private final Boolean combined;

    // foo-development.properties
    @Value("${bar}")
    private String bar;

    // foo-development.properties
    @Value("${foo}")
    private String foo;

    // foo.properties
    @Value("${democonfigclient.message}")
    private String message;

    @GetMapping("/bar")
    public String bar() {
        return bar;
    }

    @GetMapping("/foo")
    public String foo() {
        return foo;
    }

    @GetMapping("/combined")
    public String combined() {
        return "combined: " + combined;
    }

    @GetMapping("/message")
    public String message() {
        return "democonfigclient.message: " + message;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

}
