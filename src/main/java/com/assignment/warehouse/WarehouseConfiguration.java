package com.assignment.warehouse;

import com.assignment.warehouse.repository.InventoryRepository;
import com.assignment.warehouse.repository.ProductsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarehouseConfiguration {

    @Bean
    ProductsRepository productsRepository() {
        JsonReader reader = new JsonReader();
        return new ProductsRepository(reader);
    }

    @Bean
    InventoryRepository inventoryRepository() {
        JsonReader reader = new JsonReader();
        return new InventoryRepository(reader);
    }
}
