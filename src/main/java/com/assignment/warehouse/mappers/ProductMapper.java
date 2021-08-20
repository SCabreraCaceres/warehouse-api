package com.assignment.warehouse.mappers;

import com.assignment.warehouse.domain.product.Product;
import com.assignment.warehouse.domain.product.ProductArticle;
import com.assignment.warehouse.infra.product.ProductArticleDTO;
import com.assignment.warehouse.infra.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target="productArticles", source="containArticles")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target="id", source="artId")
    @Mapping(target="quantity", source="amountOf")
    ProductArticle toProductArticles(ProductArticleDTO productArticleDTO);
}
