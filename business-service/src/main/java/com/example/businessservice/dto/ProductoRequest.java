package com.example.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private CategoriaDTO categoria; // Puede incluir solo ID si se prefiere, pero mandaremos objeto completo por simplicidad
    private InventarioDTO inventario;
}