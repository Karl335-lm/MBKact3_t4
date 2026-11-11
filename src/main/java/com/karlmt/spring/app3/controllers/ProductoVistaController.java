package com.karlmt.spring.app3.controllers;

import com.karlmt.spring.app3.models.Producto;
import com.karlmt.spring.app3.services.ProductoService;
import com.karlmt.spring.app3.services.CategoriaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoVistaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoVistaController(
            ProductoService productoService,
            CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "productos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listar());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarPorId(id);

        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listar());

        return "productos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}