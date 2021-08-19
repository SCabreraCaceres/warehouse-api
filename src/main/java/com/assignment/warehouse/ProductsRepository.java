package com.assignment.warehouse;

import com.assignment.warehouse.infra.product.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductsRepository {

    private static final String PRODUCTS_JSON = "products.json";

    private final JsonReader readFile;

    public Optional<Products> getProducts() {
        Products products;
        try {
            products = readFile.deserializeTo(PRODUCTS_JSON, Products.class);
        } catch (ReadFileException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(products);
    }
}
