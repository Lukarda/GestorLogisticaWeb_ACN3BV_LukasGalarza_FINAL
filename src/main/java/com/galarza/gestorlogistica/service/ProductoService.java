package com.galarza.gestorlogistica.service;

import com.galarza.gestorlogistica.model.Producto;
import com.galarza.gestorlogistica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    public void actualizar(Long id, Producto producto) {
        Producto existente = obtenerPorId(id);
        if (existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setDescripcion(producto.getDescripcion());
            existente.setStock(producto.getStock());
            existente.setTipo(producto.getTipo());
            productoRepository.save(existente);
        }
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}