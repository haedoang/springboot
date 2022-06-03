package io.haedoang.springsamplecaching.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * fileName : User
 * author : haedoang
 * date : 2022-06-03
 * description :
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
