package com.example.dataservice.controller;

import com.example.dataservice.entity.Categoria;
import com.example.dataservice.entity.Inventario;
import com.example.dataservice.entity.Producto;
import com.example.dataservice.service.CategoriaService;
import com.example.dataservice.service.InventarioService;
import com.example.dataservice.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final InventarioService inventarioService;

    public DataController(ProductoService productoService,
                          CategoriaService categoriaService,
                          InventarioService inventarioService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.inventarioService = inventarioService;
    }

    // --- Endpoints de Productos ---

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardar(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productos/categoria/{nombre}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(nombre));
    }

    // --- Endpoints de Categorías ---

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.guardar(categoria), HttpStatus.CREATED);
    }

    // --- Endpoints de Inventario ---

    @GetMapping("/inventario/stock-bajo")
    public ResponseEntity<List<Inventario>> obtenerProductosConStockBajo() {
        return ResponseEntity.ok(inventarioService.obtenerProductosConStockBajo());
    }

    @GetMapping("/inventario/producto/{id}")
    public ResponseEntity<Inventario> obtenerInventarioPorProducto(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(id));
    }
}