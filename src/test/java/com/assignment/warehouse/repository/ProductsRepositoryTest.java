package com.assignment.warehouse.repository;

import com.assignment.warehouse.JsonReader;
import com.assignment.warehouse.ReadFileException;
import com.assignment.warehouse.infra.product.ProductArticleDTO;
import com.assignment.warehouse.infra.product.ProductDTO;
import com.assignment.warehouse.infra.product.ProductsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductsRepositoryTest {

    private static final String NAME = "name";
    private static final String PRODUCTS_JSON = "products.json";

    @Mock
    JsonReader fileReader;

    @InjectMocks
    private ProductsRepository repository;

    @Test
    void emptyProductsList() throws ReadFileException {
        given(fileReader.deserializeTo(PRODUCTS_JSON, ProductsDTO.class)).willReturn(productsEmptyList());

        assertEquals(0, repository.getProducts().get().getProducts().size());
    }

    @Test
    void emptyProductsListWhenThrownException() throws ReadFileException {
        given(fileReader.deserializeTo(PRODUCTS_JSON, ProductsDTO.class)).willThrow(ReadFileException.class);

        assertEquals(Optional.empty(), repository.getProducts());
    }

    @Test
    void getsProducts() throws ReadFileException {
        given(fileReader.deserializeTo(PRODUCTS_JSON, ProductsDTO.class)).willReturn(productsList());

        assertEquals(NAME, repository.getProducts().get().getProducts().get(0).getName());
    }

    private ProductsDTO productsEmptyList() {
        ProductsDTO products = new ProductsDTO();
        List<ProductDTO> productsList = new ArrayList<>();

        products.setProducts(productsList);

        return products;
    }

    private ProductsDTO productsList() {
        ProductsDTO products = new ProductsDTO();
        List<ProductDTO> productsList = new ArrayList<>();
        ProductDTO product = new ProductDTO();

        product.setName(NAME);
        product.setContainArticles(List.of(new ProductArticleDTO()));

        productsList.add(product);
        products.setProducts(productsList);

        return products;
    }
}
