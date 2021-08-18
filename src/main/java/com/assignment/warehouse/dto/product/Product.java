package com.assignment.warehouse.dto.product;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class Product {
    private String name;

    @SerializedName("contain_articles")
    public List<Article> containArticles;
}
