package io.haedoang.springmybatis.persistence.mapper;

import io.haedoang.springmybatis.entity.Article;
import io.haedoang.springmybatis.mapper.ArticleMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@SpringBootTest
class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    @DisplayName("리스트 조회 테스트")
    public void list() {
        // when
        final List<Article> actual = articleMapper.list();

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("단일 객체 조회")
    public void findById() {
        // when
        final Article actual = articleMapper.findOne(1L).get();

        // then
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않은 객체 조회")
    public void findByIdFailByNotExist() {
        // when
        final Optional<Article> actual = articleMapper.findOne(Long.MAX_VALUE);

        // then
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    @DisplayName("등록하기")
    public void save() {
        // given
        Article article = Article.valueOf("안녕하세요", "김해동");

        // when
        articleMapper.save(article);

        // then
        assertThat(article.getId()).isNotNull();
    }

    @Test
    @DisplayName("수정")
    public void update() {
        //given
        final Article savedArticle = articleMapper.findOne(1L).get();
        final Article updateArticle = Article.valueOf("변경된 제목", "김변경");

        // when
        savedArticle.update(updateArticle);
        articleMapper.update(savedArticle);

        // then
        assertThat(articleMapper.findOne(savedArticle.getId()).get()).extracting(Article::getTitle)
                .isEqualTo("변경된 제목");
    }

    @Test
    @DisplayName("삭제")
    public void delete() {
        // when
        articleMapper.delete(1L);

        // then
        assertThat(articleMapper.list()).hasSize(1);
    }
}

