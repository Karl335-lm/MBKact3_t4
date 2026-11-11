package com.karlmt.spring.app3.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.karlmt.spring.app3.models.Producto;
import com.karlmt.spring.app3.models.Categoria;
import com.karlmt.spring.app3.repositories.ProductoRepository;
import com.karlmt.spring.app3.repositories.CategoriaRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardar(Producto producto) {

        if (producto.getCategoria() == null ||
            producto.getCategoria().getId() == null) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Debe seleccionar una categoria"
            );
        }

        Long categoriaId = producto.getCategoria().getId();

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "La categoria no existe"
                ));

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}