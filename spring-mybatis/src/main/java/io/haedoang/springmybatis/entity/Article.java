package io.haedoang.springmybatis.entity;

import lombok.Data;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@Data
public class Article {
    private Long id;
    private String title;
    private String author;

    private Article(String title, String author) {
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
    }

    public static Article valueOf(String title, String author) {
        return new Article(title, author);
    }

    public void update(Article updateArticle) {
        this.title = updateArticle.title;
        this.author = updateArticle.author;
    }
}
