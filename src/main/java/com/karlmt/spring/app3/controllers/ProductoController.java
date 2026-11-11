package com.karlmt.spring.app3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karlmt.spring.app3.models.Producto;
import com.karlmt.spring.app3.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Consultar todos los productos
    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    // Consultar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {

        Producto producto = productoService.buscarPorId(id);

        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    // Registrar producto
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody Producto producto) {

        Producto existente = productoService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setCategoria(producto.getCategoria());

        return ResponseEntity.ok(productoService.guardar(existente));
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        Producto producto = productoService.buscarPorId(id);

        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        productoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}