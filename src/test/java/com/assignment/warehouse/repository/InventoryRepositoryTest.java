package com.assignment.warehouse.repository;

import com.assignment.warehouse.JsonReader;
import com.assignment.warehouse.ReadFileException;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.inventory.Inventory;
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
        given(fileReader.deserializeTo(INVENTORY_JSON, Inventory.class)).willReturn(inventoryEmptyList());

        assertEquals(0, repository.getInventory().get().getArticleDTOS().size());
    }


    @Test
    void emptyInventoryListWhenThrownException() throws ReadFileException {
        given(fileReader.deserializeTo(INVENTORY_JSON, Inventory.class)).willThrow(ReadFileException.class);

        assertEquals(Optional.empty(), repository.getInventory());
    }

    @Test
    void getsInventory() throws ReadFileException {
        given(fileReader.deserializeTo(INVENTORY_JSON, Inventory.class)).willReturn(articlesList());

        List<ArticleDTO> articleDTOS = repository.getInventory().get().getArticleDTOS();

        assertEquals(ART_ID, articleDTOS.get(0).getArtId());
        assertEquals(NAME, articleDTOS.get(0).getName());
        assertEquals(STOCK, articleDTOS.get(0).getStock());
    }

    private Inventory articlesList() {
        Inventory inventory = new Inventory();
        List<ArticleDTO> articlesList = new ArrayList<>();
        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setArtId(ART_ID);
        articleDTO.setName(NAME);
        articleDTO.setStock(STOCK);

        articlesList.add(articleDTO);
        inventory.setArticleDTOS(articlesList);

        return inventory;
    }

    private Inventory inventoryEmptyList() {
        Inventory inventory = new Inventory();
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        inventory.setArticleDTOS(articleDTOList);

        return inventory;
    }
}
