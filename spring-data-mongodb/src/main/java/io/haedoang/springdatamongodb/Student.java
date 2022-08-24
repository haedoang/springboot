package io.haedoang.springdatamongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Data
@Document
public class Student {
    @Id
    private String id; // generate
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favoriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    public Student(String firstName, String lastName, String email, Gender gender, Address address, List<String> favoriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favoriteSubjects = favoriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }

    public void update(Student student) {
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.gender = student.gender;
        this.address = student.address;
        this.favoriteSubjects = student.favoriteSubjects;
        this.totalSpentInBooks = student.totalSpentInBooks;
        this.created = student.created;
    }
}
