package com.example.businessservice.service;

import com.example.businessservice.client.DataServiceClient;
import com.example.businessservice.dto.ProductoDTO;
import com.example.businessservice.dto.ProductoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private ProductoBusinessService productoBusinessService;

    @Test
    void cuandoObtenerTodosLosProductos_entoncesRetornaLista() {
        // Arrange (Preparar)
        List<ProductoDTO> productosEsperados = Arrays.asList(
                new ProductoDTO(1L, "Producto 1", "Desc 1", BigDecimal.valueOf(100), null, null),
                new ProductoDTO(2L, "Producto 2", "Desc 2", BigDecimal.valueOf(200), null, null)
        );
        when(dataServiceClient.obtenerTodosLosProductos()).thenReturn(productosEsperados);

        // Act (Ejecutar)
        List<ProductoDTO> resultado = productoBusinessService.obtenerTodosLosProductos();

        // Assert (Verificar)
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(dataServiceClient).obtenerTodosLosProductos();
    }

    @Test
    void cuandoCrearProductoConPrecioNegativo_entoncesLanzaExcepcion() {
        // Arrange
        ProductoRequest request = new ProductoRequest();
        request.setNombre("Producto Test");
        request.setPrecio(BigDecimal.valueOf(-10)); // Precio inválido

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoBusinessService.crearProducto(request);
        });

        // Verificamos que NUNCA se llame al cliente si la validación falla
        verify(dataServiceClient, never()).crearProducto(any());
    }
}