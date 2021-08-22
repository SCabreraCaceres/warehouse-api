package com.assignment.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class WarehouseApplicationIT {

	@Autowired
	TestRestTemplate client;

	@Test
	void endpointReturnsWeatherInfo() {
		ResponseEntity<List> entity = client.getForEntity("/v1/warehouse/products", List.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertNotNull(entity.getBody());
	}


}
