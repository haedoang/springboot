package io.haedoang.springdatamongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
public interface UserRepository extends MongoRepository<User, String> {
}
