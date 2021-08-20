package com.assignment.warehouse.domain.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product {
    private String name;
    private List<ProductArticle> productArticles;
}
