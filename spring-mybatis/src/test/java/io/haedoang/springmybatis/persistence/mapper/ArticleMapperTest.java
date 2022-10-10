package io.haedoang.springmybatis.persistence.mapper;

import io.haedoang.springmybatis.config.DBConfig;
import io.haedoang.springmybatis.entity.Article;
import io.haedoang.springmybatis.mapper.ArticleMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@SpringBootTest
@ContextConfiguration(classes = DBConfig.class)
class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    @DisplayName("")
    public void list() {
        // given
        ;

        // when
        System.out.println(articleMapper.findOne(1L));
        // then
    }

}