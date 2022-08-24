package io.haedoang.springdatamongodbboard.board.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
public interface BoardRepository extends MongoRepository<Board, String> {

    List<Board> findBoardsByDeleteYnIsFalse();
}
