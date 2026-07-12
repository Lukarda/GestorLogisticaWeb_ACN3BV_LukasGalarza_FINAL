package com.galarza.gestorlogistica.controller;

import com.galarza.gestorlogistica.model.Incidencia;
import com.galarza.gestorlogistica.model.Producto;
import com.galarza.gestorlogistica.model.Usuario;
import com.galarza.gestorlogistica.repository.IncidenciaRepository;
import com.galarza.gestorlogistica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarIncidencias(Model model, HttpSession session) {
        model.addAttribute("incidencias", incidenciaRepository.findAll());
        model.addAttribute("usuarioLogueado", session.getAttribute("usuario"));
        return "incidencias_listar";
    }

    @GetMapping("/nuevo/{productoId}")
    public String nuevaIncidencia(@PathVariable Long productoId, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null) {
            return "redirect:/productos";
        }
        Producto producto = productoService.obtenerPorId(productoId);
        model.addAttribute("producto", producto);
        model.addAttribute("incidencia", new Incidencia());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "incidencia_form";
    }

    @PostMapping("/nuevo/{productoId}")
    public String guardarIncidencia(@PathVariable Long productoId, @ModelAttribute Incidencia incidencia, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null) {
            return "redirect:/productos";
        }
        Producto producto = productoService.obtenerPorId(productoId);
        incidencia.setProducto(producto);
        incidencia.setUsuario(usuarioLogueado);
        incidenciaRepository.save(incidencia);

        model.addAttribute("incidencia", incidencia);
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "incidencia_confirmacion";
    }
}