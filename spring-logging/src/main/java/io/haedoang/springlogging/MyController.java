package io.haedoang.springlogging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName : Controller
 * author : haedoang
 * date : 2022-06-07
 * description :
 */
@Slf4j
@RestController
public class MyController {

    @GetMapping
    public String index() {
        log.trace("hello, trace");
        log.debug("hello, debug");
        log.info("hello, info");
        log.warn("hello, warn");
        log.error("hello, error");
        return "hello";
    }

}
