package io.haedoang.springdatamongodbboard.board.application;

import io.haedoang.springdatamongodbboard.board.application.dto.BoardSaveRequest;
import io.haedoang.springdatamongodbboard.board.domain.Board;
import io.haedoang.springdatamongodbboard.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board save(BoardSaveRequest request) {
        Board savedBoard = boardRepository.insert(request.toEntity());
        return savedBoard;
    }

    public List<Board> list() {
        return boardRepository.findBoardsByDeleteYnIsFalse();
    }

    public Board findById(String id) {
        return boardRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    public void update(String id, Board board) {
        Board savedBoard = this.findById(id);
        savedBoard.update(board);
        boardRepository.save(savedBoard);
    }

    public void deleteById(String id) {
        boardRepository.deleteById(id);
    }
}
