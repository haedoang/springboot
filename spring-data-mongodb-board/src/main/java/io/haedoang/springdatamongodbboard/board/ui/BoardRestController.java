package io.haedoang.springdatamongodbboard.board.ui;

import io.haedoang.springdatamongodbboard.board.application.BoardService;
import io.haedoang.springdatamongodbboard.board.application.dto.BoardSaveRequest;
import io.haedoang.springdatamongodbboard.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@RestController
@RequestMapping("api/v1/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> save(@RequestBody BoardSaveRequest request) {
        Board response = boardService.save(request);
        return ResponseEntity.created(URI.create("api/v1/boards/" + response.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Board>> list() {
        List<Board> response = boardService.list();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Board board) {
        boardService.update(id, board);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        boardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
