package com.assignment.warehouse.infra.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleDTO {
    @SerializedName("art_id")
    private Integer artId;

    private String name;

    private Integer stock;
}
