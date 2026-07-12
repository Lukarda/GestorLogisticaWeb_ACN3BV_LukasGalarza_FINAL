package com.galarza.gestorlogistica.controller;

import com.galarza.gestorlogistica.model.Producto;
import com.galarza.gestorlogistica.service.ProductoService;
import com.galarza.gestorlogistica.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model, HttpSession session) {
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("usuarioLogueado", session.getAttribute("usuario"));
        return "listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", new Producto());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "form";
    }

    @PostMapping
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/productos";
        }
        if (result.hasErrors()) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
            return "form";
        }
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/productos";
        }
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "form";
    }

    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable Long id, @Valid @ModelAttribute("producto") Producto producto, BindingResult result, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/productos";
        }
        if (result.hasErrors()) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
            return "form";
        }
        productoService.actualizar(id, producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/productos";
        }
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}