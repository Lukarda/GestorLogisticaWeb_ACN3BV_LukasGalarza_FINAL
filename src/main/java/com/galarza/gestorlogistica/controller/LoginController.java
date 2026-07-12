package com.galarza.gestorlogistica.controller;

import com.galarza.gestorlogistica.model.Usuario;
import com.galarza.gestorlogistica.model.Producto;
import com.galarza.gestorlogistica.repository.UsuarioRepository;
import com.galarza.gestorlogistica.service.UsuarioService;
import com.galarza.gestorlogistica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/login")
    public String mostrarLoginAlternativo() {
        return "login";
    }

    @PostMapping("/listar")
    public String loginPost(@RequestParam String nombreUsuario,
                            @RequestParam String contraseña,
                            Model model,
                            HttpSession session) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (usuario != null && usuarioService.verificarPassword(contraseña, usuario.getContraseña())) {
            session.setAttribute("usuario", usuario);
            return "redirect:/listar";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/listar")
    public String mostrarProductos(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }
        List<Producto> productos = productoService.listarTodos();
        model.addAttribute("productos", productos);
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "listar";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}