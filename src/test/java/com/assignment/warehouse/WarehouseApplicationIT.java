package com.assignment.warehouse;

import com.assignment.warehouse.infra.product.Product;
import com.assignment.warehouse.infra.product.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseApplicationIT {

	@Autowired
	private ProductsRepository productsRepository;

	@Test
	void returnProductsList() {
		Optional<Products> products = productsRepository.getProducts();

		assertEquals(2, products.get().getProducts().size());
		Product product = products.get().getProducts().get(0);
		assertEquals("Dining Chair", product.getName());
		assertEquals(1, product.getContainArticles().get(0).getArtId());
		assertEquals(4, product.getContainArticles().get(0).getAmountOf());
	}

}
