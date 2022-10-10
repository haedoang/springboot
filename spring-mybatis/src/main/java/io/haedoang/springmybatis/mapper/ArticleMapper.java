package io.haedoang.springmybatis.mapper;

import io.haedoang.springmybatis.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@Component
@Mapper
public interface ArticleMapper {
    Optional<Article> findOne(Long id);
}
