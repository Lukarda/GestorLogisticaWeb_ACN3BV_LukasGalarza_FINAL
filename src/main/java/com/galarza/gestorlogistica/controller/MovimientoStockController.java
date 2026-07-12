package com.galarza.gestorlogistica.controller;

import com.galarza.gestorlogistica.model.MovimientoStock;
import com.galarza.gestorlogistica.model.Producto;
import com.galarza.gestorlogistica.model.Usuario;
import com.galarza.gestorlogistica.repository.MovimientoStockRepository;
import com.galarza.gestorlogistica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/movimientos")
public class MovimientoStockController {

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarMovimientos(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null || usuarioLogueado.getId() == null) {
            return "redirect:/productos";
        }
        List<MovimientoStock> movimientos;
        if ("admin".equals(usuarioLogueado.getRol())) {
            movimientos = movimientoStockRepository.findAll()
                    .stream()
                    .filter(m -> m.getTipoMovimiento() == MovimientoStock.TipoMovimiento.ingreso)
                    .toList();
        } else if ("logistico".equals(usuarioLogueado.getRol())) {
            movimientos = movimientoStockRepository.findByUsuarioId(usuarioLogueado.getId().longValue());
        } else {
            return "redirect:/productos";
        }
        model.addAttribute("movimientos", movimientos);
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "movimientos_listar";
    }

    @GetMapping("/nuevo")
    public String nuevoMovimiento(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null || usuarioLogueado.getId() == null || !"logistico".equals(usuarioLogueado.getRol())) {
            return "redirect:/productos";
        }
        List<Producto> productos = productoService.listarTodos();
        model.addAttribute("productos", productos);
        model.addAttribute("movimiento", new MovimientoStock());
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "movimiento_form";
    }

    @PostMapping("/nuevo")
    public String registrarMovimiento(@ModelAttribute MovimientoStock movimiento, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null || usuarioLogueado.getId() == null || !"logistico".equals(usuarioLogueado.getRol())) {
            return "redirect:/productos";
        }
        Producto producto = productoService.obtenerPorId(movimiento.getProducto().getId());
        movimiento.setProducto(producto);
        movimiento.setUsuario(usuarioLogueado);
        movimientoStockRepository.save(movimiento);
        model.addAttribute("movimiento", movimiento);
        model.addAttribute("usuarioLogueado", usuarioLogueado);
        return "movimiento_confirmacion";
    }
}