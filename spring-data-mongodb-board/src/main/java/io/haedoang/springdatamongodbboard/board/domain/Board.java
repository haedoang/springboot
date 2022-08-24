package io.haedoang.springdatamongodbboard.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    private String id;

    private String upBoardId;

    private String title;

    private String content;

    private String writer;

    private Boolean deleteYn = Boolean.FALSE;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board(String upBoardId, String title, String content, String writer, Boolean deleteYn) {
        this.upBoardId = upBoardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.deleteYn = deleteYn;
    }

    public void update(Board board) {
        if (!writer.equals(board.writer)) {
            throw new RuntimeException();
        }

        this.title = board.title;
        this.content = board.content;
    }
}
