package com.galarza.gestorlogistica.controller;

import com.galarza.gestorlogistica.model.Usuario;
import com.galarza.gestorlogistica.service.UsuarioService;
import com.galarza.gestorlogistica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null || !usuarioLogueado.getRol().equals("admin")) {
            return "redirect:/";
        }
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "usuarios_listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/usuarios";
        }
        Usuario usuario = new Usuario();
        usuario.setRol("logistico");
        model.addAttribute("usuario", usuario);
        return "usuario_form";
    }

    @PostMapping
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/usuarios";
        }
        if (result.hasErrors()) {
            return "usuario_form";
        }
        if (usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()) != null) {
            model.addAttribute("error", "El nombre de usuario ya está en uso.");
            return "usuario_form";
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/usuarios";
        }
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario);
        return "usuario_form";
    }

    @PostMapping("/editar/{id}")
    public String actualizarUsuario(@PathVariable Integer id, @Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/usuarios";
        }
        if (result.hasErrors()) {
            return "usuario_form";
        }
        Usuario existente = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        if (existente != null && !existente.getId().equals(id)) {
            model.addAttribute("error", "El nombre de usuario ya está en uso.");
            return "usuario_form";
        }
        usuario.setId(id);
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado != null && usuarioLogueado.getRol().equals("logistico")) {
            return "redirect:/usuarios";
        }
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}