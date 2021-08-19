package com.assignment.warehouse.infra.product;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Article {

    @SerializedName("art_id")
    private Integer artId;

    @SerializedName("amount_of")
    private Integer amountOf;
}
