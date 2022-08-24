package io.haedoang.springdatamongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findStudentByEmail(String email);
}
