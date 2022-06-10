package io.haedoang.springsamplerest.ui;

import io.haedoang.springsamplerest.application.UserService;
import io.haedoang.springsamplerest.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

/**
 * fileName : UserRestController
 * author : haedoang
 * date : 2022-06-10
 * description :
 */
@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String age) {
        return ResponseEntity.ok().body(userService.list());
    }

    @PostMapping(value = "/json", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveJson(@RequestBody User user) {
        User savedUser = userService.save(user);

        return ResponseEntity.created(URI.create("api/v1/users/json/" + savedUser.getId())).body(savedUser);
    }

    @PostMapping(value = "/form", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<User> saveFormData(User user, HttpServletRequest request) {
        printHeaders(request);
        User savedUser = userService.save(user);

        return ResponseEntity.created(URI.create("api/v1/users/form/" + savedUser.getId())).body(savedUser);
    }

    @PostMapping(value = "/multipart", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> saveMultipart(@RequestPart(value = "files", required = false) MultipartFile[] files, HttpServletRequest request) {
        printHeaders(request);

        return ResponseEntity.ok().body(Arrays.stream(files).map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList()));
    }


    @PostMapping(value = "/multipart2", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> saveMultipartWithObject(@RequestPart(value = "files", required = false) MultipartFile[] files, @ModelAttribute User user, HttpServletRequest request) {
        printHeaders(request);
        User savedUser = userService.save(user);
        log.info("uploadFiles : {}", Arrays.stream(files)
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList()));
        return ResponseEntity.created(URI.create("api/v1/users/multipart2/" + savedUser.getId())).body(savedUser);
    }

    private void printHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("Header Name - {}, Value - {}", headerName, request.getHeader(headerName));
        }
    }
}
