package com.assignment.warehouse;

import com.assignment.warehouse.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductsControllerTest {

    private static final String NAME = "name";
    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductsController productsController;

    @Test
    public void getsEmptyProductsList() {
        given(productsService.getProducts()).willReturn(List.of());

        List<Product> products = productsController.getProducts().getBody();

        assertEquals(0, products.size());
    }

    @Test
    void throwsException() {
        given(productsService.getProducts()).willThrow(new RestClientException(""));

        assertEquals(ResponseEntity.noContent().build(), productsController.getProducts());
    }

    @Test
    public void getsProducts() {
        given(productsService.getProducts()).willReturn(buildProducts());

        ResponseEntity<List<Product>> actual = productsController.getProducts();

        assertEquals(ResponseEntity.ok(buildProducts()).getStatusCode(),
                actual.getStatusCode());
        assertEquals(1, actual.getBody().size());
        assertEquals(NAME, actual.getBody().get(0).getName());
        assertEquals(1, actual.getBody().get(0).getQuantity());
    }

    private List<Product> buildProducts() {
        return List.of(Product.builder()
                .name(NAME)
                .quantity(1)
                .build());
    }

}
