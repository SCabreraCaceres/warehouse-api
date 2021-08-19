package com.assignment.warehouse.repository;

import com.assignment.warehouse.JsonReader;
import com.assignment.warehouse.ReadFileException;
import com.assignment.warehouse.infra.inventory.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    private static final String INVENTORY_JSON = "inventory.json";

    private final JsonReader readFile;

    public Optional<Inventory> getInventory() {
        Inventory inventory;
        try {
            inventory = readFile.deserializeTo(INVENTORY_JSON, Inventory.class);
        } catch (ReadFileException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(inventory);
    }
}
