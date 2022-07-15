package io.haedoang.springbatch.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Data
@Entity
public class People {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    protected People() {
    }

    public People(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
