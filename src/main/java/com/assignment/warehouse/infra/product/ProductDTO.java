package com.assignment.warehouse.infra.product;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private String name;

    @SerializedName("contain_articles")
    public List<ProductArticleDTO> containArticles;
}
