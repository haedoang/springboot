package io.haedoang.springdatamongodbboard;

import io.haedoang.springdatamongodbboard.board.domain.Board;
import io.haedoang.springdatamongodbboard.board.domain.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BoardRepository boardRepository) {
        return args -> {
            Board savedBoard =
                    boardRepository.insert(new Board(null, "삭제된 게시글제목", "삭제된 게시글 내용입니다.", "관리자", Boolean.TRUE));
            log.info("init Data : {}", savedBoard);
        };
    }
}
