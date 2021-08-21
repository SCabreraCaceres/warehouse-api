package com.assignment.warehouse;

import com.assignment.warehouse.domain.product.Product;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.inventory.InventoryDTO;
import com.assignment.warehouse.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = WarehouseApplication.class)
class WarehouseApplicationIT {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ProductsService productsService;


	@Test
	void getsInventory() {
		Optional<InventoryDTO> inventory = inventoryRepository.getInventory();

		assertEquals(4, inventory.get().getArticles().size());
		ArticleDTO articleDTO = inventory.get().getArticles().get(0);
		assertEquals(1, articleDTO.getArtId());
		assertEquals("leg", articleDTO.getName());
		assertEquals(12, articleDTO.getStock());
	}

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
