package com.assignment.warehouse;

import com.assignment.warehouse.infra.product.Products;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonReaderTest {
    private JsonReader jsonReader = new JsonReader();

    @Test
    void readContentFromFile() throws IOException {
        Products products = jsonReader.deserializeTo("products.json", Products.class);

        assertEquals("Dining Chair", products.getProducts().get(0).getName());
        assertEquals(1, products.getProducts().get(0).getContainArticles().get(0).getArtId());
        assertEquals(4, products.getProducts().get(0).getContainArticles().get(0).getAmountOf());
    }

    @Test
    void throwsExceptionNoFile() {
        assertThrows(ReadFileException.class,
                () -> jsonReader.deserializeTo("", Products.class));
    }

    @Test
    void throwsExceptionWhenFileDoesNotExist() {
        assertThrows(ReadFileException.class,
                () -> jsonReader.deserializeTo("test.json", Products.class));
    }
}
