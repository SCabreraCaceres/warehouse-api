package com.assignment.warehouse;

import com.assignment.warehouse.infra.product.ProductsDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonReaderTest {
    private JsonReader jsonReader = new JsonReader();

    @Test
    void readContentFromFile() throws IOException {
        ProductsDTO productsDTO = jsonReader.deserializeTo("products.json", ProductsDTO.class);

        assertEquals("Dining Chair", productsDTO.getProducts().get(0).getName());
        assertEquals(1, productsDTO.getProducts().get(0).getContainArticles().get(0).getArtId());
        assertEquals(4, productsDTO.getProducts().get(0).getContainArticles().get(0).getAmountOf());
    }

    @Test
    void throwsExceptionNoFile() {
        assertThrows(ReadFileException.class,
                () -> jsonReader.deserializeTo("", ProductsDTO.class));
    }

    @Test
    void throwsExceptionWhenFileDoesNotExist() {
        assertThrows(ReadFileException.class,
                () -> jsonReader.deserializeTo("test.json", ProductsDTO.class));
    }
}
