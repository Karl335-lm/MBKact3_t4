package com.karlmt.spring.app3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karlmt.spring.app3.models.Categoria;
import com.karlmt.spring.app3.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Consultar todas las categorias
    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    // Consultar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoria);
    }

    // Registrar categoria
    @PostMapping
    public Categoria guardar(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    // Actualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        Categoria existente = categoriaService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());

        return ResponseEntity.ok(categoriaService.guardar(existente));
    }

    // Eliminar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        Categoria categoria = categoriaService.buscarPorId(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        categoriaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
