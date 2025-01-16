package io.haedoang.serviceapi.infra.UserClient;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate userRestTemplate;

    public String greeting() {
        return userRestTemplate.getForObject("/greeting", String.class);
    }
}
