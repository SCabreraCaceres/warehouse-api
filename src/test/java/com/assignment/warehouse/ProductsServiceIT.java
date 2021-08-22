package com.assignment.warehouse;

import com.assignment.warehouse.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = WarehouseApplication.class)
class ProductsServiceIT {

	@Autowired
	private ProductsService productsService;

	@Test
	void serviceGetsProductsList() {
		List<Product> products = productsService.getProducts();

		assertEquals(2, products.size());

		Product product = products.get(0);
		assertEquals("Dining Chair", product.getName());
		assertEquals(2, product.getQuantity());

		Product product2 = products.get(1);
		assertEquals("Dining Table", product2.getName());
		assertEquals(1, product2.getQuantity());
	}


}
