package com.assignment.warehouse.repository;

import com.assignment.warehouse.JsonReader;
import com.assignment.warehouse.ReadFileException;
import com.assignment.warehouse.infra.product.ProductsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductsRepository {

    private static final String PRODUCTS_JSON = "products.json";

    private final JsonReader jsonReader;

    public Optional<ProductsDTO> getProducts() {
        ProductsDTO products;
        try {
            products = jsonReader.deserializeTo(PRODUCTS_JSON, ProductsDTO.class);
        } catch (ReadFileException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(products);
    }
}
