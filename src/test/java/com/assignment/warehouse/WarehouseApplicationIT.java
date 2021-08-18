package com.assignment.warehouse;

import com.assignment.warehouse.dto.product.Products;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseApplicationIT {

	private ReadFile readFile = new ReadFile();

	@Test
	void returnProductsList() throws ReadFileException {
		Products products = readFile.deserializeTo("products.json", Products.class);
		assertEquals(2, products.getProducts().size());
	}

}
