package com.assignment.warehouse.repository;

import com.assignment.warehouse.JsonReader;
import com.assignment.warehouse.ReadFileException;
import com.assignment.warehouse.infra.inventory.ArticleDTO;
import com.assignment.warehouse.infra.inventory.InventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    private static final String INVENTORY_JSON = "inventory.json";

    private final JsonReader jsonReader;

    public ArticleDTO getInventoryByArtId(Integer artId) {
        return getInventory().get().getArticles()
                .stream().filter(
                        articleDTO -> articleDTO.getArtId().equals(artId)
                )
                .findFirst().get();
    }

    // TODO Refactor, make private
    public Optional<InventoryDTO> getInventory() {
        InventoryDTO inventoryDTO;
        try {
            inventoryDTO = jsonReader.deserializeTo(INVENTORY_JSON, InventoryDTO.class);
        } catch (ReadFileException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(inventoryDTO);
    }
}
