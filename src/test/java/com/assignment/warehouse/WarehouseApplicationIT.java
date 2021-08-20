package com.assignment.warehouse;

import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.inventory.Inventory;
import com.assignment.warehouse.infra.product.ProductDTO;
import com.assignment.warehouse.infra.product.Products;
import com.assignment.warehouse.repository.InventoryRepository;
import com.assignment.warehouse.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = WarehouseConfiguration.class)
class WarehouseApplicationIT {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Test
	void getsProductsList() {
		Optional<Products> products = productsRepository.getProducts();

		assertEquals(2, products.get().getProducts().size());
		ProductDTO product = products.get().getProducts().get(0);
		assertEquals("Dining Chair", product.getName());
		assertEquals(1, product.getContainArticles().get(0).getArtId());
		assertEquals(4, product.getContainArticles().get(0).getAmountOf());
	}

	@Test
	void getsInventory() {
		Optional<Inventory> inventory = inventoryRepository.getInventory();

		assertEquals(4, inventory.get().getArticleDTOS().size());
		ArticleDTO articleDTO = inventory.get().getArticleDTOS().get(0);
		assertEquals(1, articleDTO.getArtId());
		assertEquals("leg", articleDTO.getName());
		assertEquals(12, articleDTO.getStock());
	}

}
