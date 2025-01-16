package io.haedoang.serviceuser.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final Environment environment;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, " + environment.getProperty("spring.application.name");
    }
}
