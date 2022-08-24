package io.haedoang.springdatamongodbboard.board.application.dto;

import io.haedoang.springdatamongodbboard.board.domain.Board;
import lombok.Data;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Data
public class BoardSaveRequest {
    private String title;
    private String content;
    private String writer;


    public Board toEntity() {
        return new Board(title, content, writer);
    }
}
