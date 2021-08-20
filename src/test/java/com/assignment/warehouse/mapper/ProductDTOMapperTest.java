package com.assignment.warehouse.mapper;

import com.assignment.warehouse.domain.product.Product;
import com.assignment.warehouse.infra.product.ProductArticleDTO;
import com.assignment.warehouse.infra.product.ProductDTO;
import com.assignment.warehouse.mappers.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDTOMapperTest {

    private static final String NAME = "name";
    private static final Integer ID = 1;
    private static final Integer QUANTITY = 1;

    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void shouldMapProductDTOtoProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(NAME);
        productDTO.setContainArticles(articles());

        Product product = mapper.toProduct(productDTO);

        assertEquals(NAME, product.getName());
        assertEquals(ID, product.getProductArticles().get(0).getId());
        assertEquals(QUANTITY, product.getProductArticles().get(0).getQuantity());
    }

    private List<ProductArticleDTO> articles() {
        ProductArticleDTO articleDTO = new ProductArticleDTO();
        articleDTO.setArtId(ID);
        articleDTO.setAmountOf(QUANTITY);
        return List.of(articleDTO);
    }
}
