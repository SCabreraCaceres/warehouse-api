package com.assignment.warehouse;

import com.assignment.warehouse.domain.product.Product;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.product.ProductArticleDTO;
import com.assignment.warehouse.infra.product.ProductDTO;
import com.assignment.warehouse.infra.product.ProductsDTO;
import com.assignment.warehouse.mappers.ProductMapper;
import com.assignment.warehouse.repository.InventoryRepository;
import com.assignment.warehouse.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    public static final Integer ID = 1;
    public static final Integer AMOUNT = 1;
    public static final String NAME = "name";

    @Mock
    private ProductsRepository productRepository;

    @Mock
    private ProductMapper mapper;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private ProductsService productsService;

    @Test
    void productsEmptyList() {
        given(productRepository.getProducts()).willReturn(Optional.of(new ProductsDTO(emptyList())));
        assertEquals(emptyList(), productsService.getProducts());
    }

    @Test
    void hasProductWithStock() {
        ArticleDTO articleDTO1 = new ArticleDTO();
        articleDTO1.setArtId(1);
        articleDTO1.setStock(12);
        ArticleDTO articleDTO2 = new ArticleDTO();
        articleDTO2.setArtId(2);
        articleDTO2.setStock(17);
        ArticleDTO articleDTO3 = new ArticleDTO();
        articleDTO3.setArtId(3);
        articleDTO3.setStock(3);

        given(productRepository.getProducts()).willReturn(Optional.of(productsList()));
        given(inventoryRepository.getInventoryByArtId(1)).willReturn(articleDTO1);
        given(inventoryRepository.getInventoryByArtId(2)).willReturn(articleDTO2);
        given(inventoryRepository.getInventoryByArtId(3)).willReturn(articleDTO3);
        given(mapper.toProduct(any(ProductDTO.class))).willReturn(Product.builder().name(NAME).build());

        List<Product> actual = productsService.getProducts();
        assertEquals(NAME, actual.get(0).getName());
        assertEquals(2, actual.get(0).getQuantity());
    }

    @Test
    void hasProductWithoutStock() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArtId(ID);
        articleDTO.setStock(AMOUNT);
        ArticleDTO articleDTO2 = new ArticleDTO();
        articleDTO2.setArtId(2);
        articleDTO2.setStock(0);
        ArticleDTO articleDTO3 = new ArticleDTO();
        articleDTO3.setArtId(3);
        articleDTO3.setStock(0);

        given(productRepository.getProducts()).willReturn(Optional.of(productsList()));
        given(inventoryRepository.getInventoryByArtId(1)).willReturn(articleDTO);
        given(inventoryRepository.getInventoryByArtId(2)).willReturn(articleDTO2);
        given(inventoryRepository.getInventoryByArtId(3)).willReturn(articleDTO3);
        given(mapper.toProduct(any(ProductDTO.class))).willReturn(Product.builder().name(NAME).build());

        List<Product> actual = productsService.getProducts();
        assertEquals(NAME, actual.get(0).getName());
        assertEquals(0, actual.get(0).getQuantity());
    }

    private ProductsDTO productsList() {
        ProductsDTO products = new ProductsDTO();
        ProductDTO productDTO = buildProductDto();

        products.setProducts(List.of(productDTO));
        return products;
    }

    private ProductDTO buildProductDto() {
        ProductArticleDTO productArticleDTO = new ProductArticleDTO();
        productArticleDTO.setArtId(1);
        productArticleDTO.setAmountOf(4);

        ProductArticleDTO productArticleDTO2 = new ProductArticleDTO();
        productArticleDTO2.setArtId(2);
        productArticleDTO2.setAmountOf(8);

        ProductArticleDTO productArticleDTO3 = new ProductArticleDTO();
        productArticleDTO3.setArtId(3);
        productArticleDTO3.setAmountOf(1);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(NAME);
        productDTO.setContainArticles(List.of(productArticleDTO, productArticleDTO2, productArticleDTO3));
        return productDTO;
    }

}
