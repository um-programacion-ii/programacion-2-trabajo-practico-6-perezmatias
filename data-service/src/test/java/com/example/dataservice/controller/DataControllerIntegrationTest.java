package com.example.dataservice.controller;

import com.example.dataservice.entity.Producto;
import com.example.dataservice.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev") // Usamos H2 para el test
class DataControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductoService productoService;

    @Test
    void cuandoCrearProducto_entoncesSePersisteCorrectamente() {
        // Arrange
        Producto producto = new Producto();
        producto.setNombre("Producto Test Integracion");
        producto.setDescripcion("Descripción de prueba");
        producto.setPrecio(BigDecimal.valueOf(100.50));

        // Act
        ResponseEntity<Producto> response = restTemplate.postForEntity(
                "/data/productos", producto, Producto.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Producto Test Integracion", response.getBody().getNombre());
    }

    @Test
    void cuandoBuscarProductoInexistente_entoncesRetorna404O500() {
        // Nota: Dependiendo de cómo manejamos la excepción globalmente, puede ser 500 o 404.
        // En nuestro caso simple lanzamos RuntimeException, que suele dar 500 por defecto en Spring.
        // Si hubiéramos configurado un ControllerAdvice sería 404.

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/data/productos/9999", String.class);

        // Assert
        assertTrue(response.getStatusCode().isError());
    }
}