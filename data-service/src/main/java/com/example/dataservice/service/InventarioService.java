package com.example.dataservice.service;

import com.example.dataservice.entity.Inventario;
import com.example.dataservice.repository.InventarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> obtenerProductosConStockBajo() {
        return inventarioRepository.findInventarioConStockBajo();
    }

    public Inventario buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto ID: " + productoId));
    }
}