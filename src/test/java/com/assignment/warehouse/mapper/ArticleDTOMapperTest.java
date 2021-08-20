package com.assignment.warehouse.mapper;

import com.assignment.warehouse.domain.article.Article;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.mappers.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleDTOMapperTest {

    private static final String NAME = "name";
    private static final Integer ID = 1;
    private static final Integer QUANTITY = 1;

    ArticleMapper mapper = Mappers.getMapper(ArticleMapper.class);

    @Test
    void shouldMapArticleDTOtoArticle() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArtId(ID);
        articleDTO.setName(NAME);
        articleDTO.setStock(QUANTITY);

        Article article = mapper.toArticle(articleDTO);

        assertEquals(ID, article.getArtId());
        assertEquals(NAME, article.getName());
        assertEquals(QUANTITY, article.getStock());
    }
}
