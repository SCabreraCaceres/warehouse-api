package com.assignment.warehouse;

import com.assignment.warehouse.infra.product.Article;
import com.assignment.warehouse.infra.product.Product;
import com.assignment.warehouse.infra.product.Products;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductsRepositoryTest {

    public static final String NAME = "name";
    @Mock
    JsonReader fileReader;

    @InjectMocks
    private ProductsRepository repository;

    @Test
    void emptyProductsList() throws ReadFileException {
        given(fileReader.deserializeTo("products.json", Products.class)).willReturn(productsEmptyList());
        assertEquals(0, repository.getProducts().get().getProducts().size());
    }

    @Test
    void emptyProductsListWhenThrownException() throws ReadFileException {
        given(fileReader.deserializeTo("products.json", Products.class)).willThrow(ReadFileException.class);
        assertEquals(Optional.empty(), repository.getProducts());
    }

    @Test
    void getsProducts() throws ReadFileException {
        given(fileReader.deserializeTo("products.json", Products.class)).willReturn(productsList());
        assertEquals(NAME, repository.getProducts().get().getProducts().get(0).getName());
    }

    private Products productsEmptyList() {
        Products products = new Products();
        List<Product> productsList = new ArrayList<>();

        products.setProducts(productsList);

        return products;
    }

    private Products productsList() {
        Products products = new Products();
        List<Product> productsList = new ArrayList<>();
        Product product = new Product();

        product.setName(NAME);
        product.setContainArticles(List.of(new Article()));

        productsList.add(product);
        products.setProducts(productsList);

        return products;
    }
}
