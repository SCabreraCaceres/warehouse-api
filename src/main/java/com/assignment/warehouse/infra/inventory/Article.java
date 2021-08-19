package com.assignment.warehouse.infra.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Article {
    @SerializedName("art_id")
    private Integer artId;

    private String name;

    private Integer stock;
}
