package com.assignment.warehouse.domain.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private List<ProductArticle> productArticles;
    private Integer quantity;
}
