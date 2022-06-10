package io.haedoang.springsamplerest.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * fileName : User
 * author : haedoang
 * date : 2022-06-10
 * description :
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String name;
    private int age;

    public static User valueOf(Long id, String name, int age) {
        return new User(id, name, age);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
