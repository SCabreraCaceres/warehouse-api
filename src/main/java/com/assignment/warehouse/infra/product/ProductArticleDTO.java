package com.assignment.warehouse.infra.product;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductArticleDTO {

    @SerializedName("art_id")
    private Integer artId;

    @SerializedName("amount_of")
    private Integer amountOf;
}
