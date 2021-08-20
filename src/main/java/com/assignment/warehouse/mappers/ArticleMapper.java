package com.assignment.warehouse.mappers;

import com.assignment.warehouse.domain.article.Article;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toArticle(ArticleDTO article);
}
