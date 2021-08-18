package com.assignment.warehouse;

import com.assignment.warehouse.dto.product.Products;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadFileTest {
    private ReadFile readFile = new ReadFile();

    @Test
    void readContentFromFile() throws IOException {
        Products products = readFile.deserializeTo("products.json", Products.class);
        assertEquals("Dining Chair", products.getProducts().get(0).getName());
        assertEquals(1, products.getProducts().get(0).getContainArticles().get(0).getArtId());
        assertEquals(4, products.getProducts().get(0).getContainArticles().get(0).getAmountOf());
    }

    @Test
    void throwsExceptionNoFile() {
        assertThrows(ReadFileException.class,
                () -> readFile.deserializeTo("", Products.class));
    }

    @Test
    void throwsExceptionWhenFileDoesNotExist() {
        assertThrows(ReadFileException.class,
                () -> readFile.deserializeTo("test.json", Products.class));
    }
}
