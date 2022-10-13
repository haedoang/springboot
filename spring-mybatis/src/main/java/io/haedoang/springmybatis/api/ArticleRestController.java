package io.haedoang.springmybatis.api;

import io.haedoang.springmybatis.application.ArticleService;
import io.haedoang.springmybatis.entity.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@RestController
@RequestMapping("api/v1/articles")
public class ArticleRestController {
    private ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> list() {
        final List<Article> response = articleService.list();

        return ResponseEntity.ok()
                .body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<Article> findOne(@PathVariable("id") Long id) {
        final Article response = articleService.findOne(id);

        return ResponseEntity.ok()
                .body(response);
    }


}
