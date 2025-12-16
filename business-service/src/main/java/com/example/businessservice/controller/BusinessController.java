package com.example.businessservice.controller;

import com.example.businessservice.dto.InventarioDTO;
import com.example.businessservice.dto.ProductoDTO;
import com.example.businessservice.dto.ProductoRequest;
import com.example.businessservice.service.ProductoBusinessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessController {

    private final ProductoBusinessService productoBusinessService;

    public BusinessController(ProductoBusinessService productoBusinessService) {
        this.productoBusinessService = productoBusinessService;
    }

    // CU-001: Gestionar Productos
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoBusinessService.obtenerTodosLosProductos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoBusinessService.obtenerProductoPorId(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoRequest request) {
        try {
            ProductoDTO nuevoProducto = productoBusinessService.crearProducto(request);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/productos/categoria/{nombre}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return ResponseEntity.ok(productoBusinessService.obtenerProductosPorCategoria(nombre));
    }

    // CU-004: Consultar Reportes
    @GetMapping("/reportes/stock-bajo")
    public ResponseEntity<List<InventarioDTO>> obtenerProductosConStockBajo() {
        return ResponseEntity.ok(productoBusinessService.obtenerProductosConStockBajo());
    }

    @GetMapping("/reportes/valor-inventario")
    public ResponseEntity<BigDecimal> obtenerValorTotalInventario() {
        return ResponseEntity.ok(productoBusinessService.calcularValorTotalInventario());
    }
}