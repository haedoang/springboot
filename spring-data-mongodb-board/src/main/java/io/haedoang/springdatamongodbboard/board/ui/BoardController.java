package io.haedoang.springdatamongodbboard.board.ui;

import io.haedoang.springdatamongodbboard.board.application.BoardService;
import io.haedoang.springdatamongodbboard.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * author : haedoang
 * date : 2022-08-24
 * description :
 */
@Controller
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {

    private  final BoardService boardService;

    @GetMapping
    public String list(Model model) {
        List<Board> boards = boardService.list();
        model.addAttribute("boards", boards);
        return "list";
    }
}
