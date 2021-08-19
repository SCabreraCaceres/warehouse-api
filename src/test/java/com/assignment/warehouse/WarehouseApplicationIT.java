package com.assignment.warehouse;

import com.assignment.warehouse.infra.inventory.Article;
import com.assignment.warehouse.infra.inventory.Inventory;
import com.assignment.warehouse.infra.product.Product;
import com.assignment.warehouse.infra.product.Products;
import com.assignment.warehouse.repository.InventoryRepository;
import com.assignment.warehouse.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseApplicationIT {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Test
	void getsProductsList() {
		Optional<Products> products = productsRepository.getProducts();

		assertEquals(2, products.get().getProducts().size());
		Product product = products.get().getProducts().get(0);
		assertEquals("Dining Chair", product.getName());
		assertEquals(1, product.getContainArticles().get(0).getArtId());
		assertEquals(4, product.getContainArticles().get(0).getAmountOf());
	}

	@Test
	void getsInventory() {
		Optional<Inventory> inventory = inventoryRepository.getInventory();

		assertEquals(4, inventory.get().getArticles().size());
		Article article = inventory.get().getArticles().get(0);
		assertEquals(1, article.getArtId());
		assertEquals("leg", article.getName());
		assertEquals(12, article.getStock());
	}

}
