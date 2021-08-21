package com.assignment.warehouse.repository;

import com.assignment.warehouse.JsonReader;
import com.assignment.warehouse.ReadFileException;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.inventory.InventoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryTest {

    private static final Integer ART_ID = 1;
    private static final String NAME = "name";
    private static final Integer STOCK = 1;
    public static final String INVENTORY_JSON = "inventory.json";

    @Mock
    JsonReader fileReader;

    @InjectMocks
    private InventoryRepository repository;

    @Test
    void emptyInventoryList() throws ReadFileException {
        given(fileReader.deserializeTo(INVENTORY_JSON, InventoryDTO.class)).willReturn(inventoryEmptyList());

        assertEquals(0, repository.getInventory().get().getArticles().size());
    }


    @Test
    void emptyInventoryListWhenThrownException() throws ReadFileException {
        given(fileReader.deserializeTo(INVENTORY_JSON, InventoryDTO.class)).willThrow(ReadFileException.class);

        assertEquals(Optional.empty(), repository.getInventory());
    }

    @Test
    void getsInventory() throws ReadFileException {
        given(fileReader.deserializeTo(INVENTORY_JSON, InventoryDTO.class)).willReturn(articlesList());

        List<ArticleDTO> articleDTOS = repository.getInventory().get().getArticles();

        assertEquals(ART_ID, articleDTOS.get(0).getArtId());
        assertEquals(NAME, articleDTOS.get(0).getName());
        assertEquals(STOCK, articleDTOS.get(0).getStock());
    }
    @Test
    void getsInventoryByArticleId() throws ReadFileException {
        given(fileReader.deserializeTo(INVENTORY_JSON, InventoryDTO.class)).willReturn(articlesList());

        ArticleDTO article = repository.getInventoryByArtId(ART_ID);

        assertEquals(ART_ID, article.getArtId());
        assertEquals(STOCK, article.getStock());
    }

    private InventoryDTO articlesList() {
        InventoryDTO inventoryDTO = new InventoryDTO();
        List<ArticleDTO> articlesList = new ArrayList<>();
        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setArtId(ART_ID);
        articleDTO.setName(NAME);
        articleDTO.setStock(STOCK);

        articlesList.add(articleDTO);
        inventoryDTO.setArticles(articlesList);

        return inventoryDTO;
    }

    private InventoryDTO inventoryEmptyList() {
        InventoryDTO inventoryDTO = new InventoryDTO();
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        inventoryDTO.setArticles(articleDTOList);

        return inventoryDTO;
    }
}
