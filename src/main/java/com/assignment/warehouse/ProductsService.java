package com.assignment.warehouse;

import com.assignment.warehouse.domain.product.Product;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.product.ProductDTO;
import com.assignment.warehouse.mappers.ProductMapper;
import com.assignment.warehouse.repository.InventoryRepository;
import com.assignment.warehouse.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductMapper productMapper;

    public List<Product> getProducts() {
        List<ProductDTO> productsDTO = productsRepository.getProducts().get().getProducts();
        if (productsDTO.isEmpty()) {
            return List.of();
        }

        List<Product> productList = new ArrayList<>();
        for (ProductDTO productDTO : productsDTO) {
            Integer quantity = productDTO.getContainArticles().stream()
                    .map(productArticleDTO -> {
                                ArticleDTO articleDTO =
                                        inventoryRepository.getInventoryByArtId(productArticleDTO.getArtId());
                                return articleDTO.getStock() / productArticleDTO.getAmountOf();
                            }
                    )
                    .filter(availableStock -> availableStock > 0)
                    .min((o1, o2) -> o1).orElse(0);

            Product product = productMapper.toProduct(productDTO);
            product.setQuantity(quantity);
            productList.add(product);
        }
        return productList;
    }

}
