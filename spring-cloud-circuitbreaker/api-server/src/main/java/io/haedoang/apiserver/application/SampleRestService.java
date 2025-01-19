package io.haedoang.apiserver.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SampleRestService {

    private static final String BASE_URI = "https://httpbin.org/";

    private final RestTemplate restTemplate;

    public Map get() {
        return restTemplate.getForObject("%s/get".formatted(BASE_URI), Map.class);
    }

    public Map delay(int seconds) {
        return restTemplate.getForObject("%s/delay/%d".formatted(BASE_URI, seconds), Map.class);
    }
}
