package com.example.businessservice.client;

import com.example.businessservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// "name" es el nombre del servicio (opcional en url hardcoded), "url" se lee del application.yml
@FeignClient(name = "data-service", url = "${data.service.url}")
public interface DataServiceClient {

    // --- Productos ---
    @GetMapping("/data/productos")
    List<ProductoDTO> obtenerTodosLosProductos();

    @GetMapping("/data/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable("id") Long id);

    @PostMapping("/data/productos")
    ProductoDTO crearProducto(@RequestBody ProductoRequest request);

    @PutMapping("/data/productos/{id}")
    ProductoDTO actualizarProducto(@PathVariable("id") Long id, @RequestBody ProductoRequest request);

    @DeleteMapping("/data/productos/{id}")
    void eliminarProducto(@PathVariable("id") Long id);

    @GetMapping("/data/productos/categoria/{nombre}")
    List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable("nombre") String nombre);

    // --- Categorías ---
    @GetMapping("/data/categorias")
    List<CategoriaDTO> obtenerTodasLasCategorias();

    @PostMapping("/data/categorias")
    CategoriaDTO crearCategoria(@RequestBody CategoriaDTO categoria);

    // --- Inventario ---
    @GetMapping("/data/inventario/stock-bajo")
    List<InventarioDTO> obtenerProductosConStockBajo();
}