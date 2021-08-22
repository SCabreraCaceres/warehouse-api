package com.assignment.warehouse;

import com.assignment.warehouse.domain.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/warehouse")
@Slf4j
public class ProductsController {

    private final ProductsService service;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        try {
            return ResponseEntity.ok(service.getProducts());
        } catch (RestClientException exception) {
            log.error("Error retrieving products: ", exception);
            return ResponseEntity.noContent().build();
        }
    }
}
