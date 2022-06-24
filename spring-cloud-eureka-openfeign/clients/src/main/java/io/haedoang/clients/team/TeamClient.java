package io.haedoang.clients.team;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * fileName : TeamClient
 * author : haedoang
 * date : 2022-06-24
 * description :
 */
@FeignClient(name = "team")
public interface TeamClient {

    @GetMapping(path = "/")
    String index();
}
