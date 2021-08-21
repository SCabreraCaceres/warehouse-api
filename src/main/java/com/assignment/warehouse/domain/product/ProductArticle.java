package com.assignment.warehouse.domain.product;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductArticle {
    private Integer id;
    private Integer quantity;
}
