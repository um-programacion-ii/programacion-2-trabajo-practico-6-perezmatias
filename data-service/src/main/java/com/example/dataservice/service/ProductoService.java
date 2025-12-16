package com.example.dataservice.service;

import com.example.dataservice.entity.Producto;
import com.example.dataservice.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        return productoRepository.findByCategoriaNombre(nombreCategoria);
    }

    public Producto guardar(Producto producto) {
        // Aseguramos la relación bidireccional con inventario si existe
        if (producto.getInventario() != null) {
            producto.getInventario().setProducto(producto);
        }
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto productoExistente = buscarPorId(id);

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        // Actualizar inventario si viene en la petición
        if (productoActualizado.getInventario() != null) {
            if (productoExistente.getInventario() == null) {
                productoExistente.setInventario(productoActualizado.getInventario());
                productoExistente.getInventario().setProducto(productoExistente);
            } else {
                productoExistente.getInventario().setCantidad(productoActualizado.getInventario().getCantidad());
                productoExistente.getInventario().setStockMinimo(productoActualizado.getInventario().getStockMinimo());
            }
        }

        return productoRepository.save(productoExistente);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}