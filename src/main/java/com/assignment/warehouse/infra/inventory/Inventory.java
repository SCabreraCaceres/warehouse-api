package com.assignment.warehouse.infra.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Inventory {
    @SerializedName("inventory")
    List<Article> articles;
}
