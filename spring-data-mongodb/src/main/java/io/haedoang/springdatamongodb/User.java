package io.haedoang.springdatamongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Data
@Document
public class User {
    @Id
    private String id;

    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
