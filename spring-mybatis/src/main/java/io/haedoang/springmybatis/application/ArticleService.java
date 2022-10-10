package io.haedoang.springmybatis.application;

import io.haedoang.springmybatis.entity.Article;
import io.haedoang.springmybatis.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@Service
public class ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Article findOne(Long id) {
        return articleMapper.findOne(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
