package com.galarza.gestorlogistica.repository;

import com.galarza.gestorlogistica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}