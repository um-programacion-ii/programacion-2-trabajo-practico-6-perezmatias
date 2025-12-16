package com.example.businessservice.service;

import com.example.businessservice.client.DataServiceClient;
import com.example.businessservice.dto.ProductoDTO;
import com.example.businessservice.dto.ProductoRequest;
import com.example.businessservice.dto.InventarioDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoBusinessService {

    private final DataServiceClient dataServiceClient;

    public ProductoBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        return dataServiceClient.obtenerTodosLosProductos();
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        return dataServiceClient.obtenerProductoPorId(id);
    }

    public ProductoDTO crearProducto(ProductoRequest request) {
        // --- Validaciones de Negocio (Requisito TP) ---
        if (request.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }

        if (request.getInventario() != null && request.getInventario().getCantidad() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        // Si pasa las validaciones, llamamos al microservicio de datos
        return dataServiceClient.crearProducto(request);
    }

    public List<ProductoDTO> obtenerProductosPorCategoria(String nombre) {
        return dataServiceClient.obtenerProductosPorCategoria(nombre);
    }

    public List<InventarioDTO> obtenerProductosConStockBajo() {
        return dataServiceClient.obtenerProductosConStockBajo();
    }

    // --- Lógica de Reporte: Valor Total del Inventario ---
    public BigDecimal calcularValorTotalInventario() {
        List<ProductoDTO> productos = dataServiceClient.obtenerTodosLosProductos();

        return productos.stream()
                .filter(p -> p.getInventario() != null)
                .map(p -> p.getPrecio().multiply(BigDecimal.valueOf(p.getInventario().getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}