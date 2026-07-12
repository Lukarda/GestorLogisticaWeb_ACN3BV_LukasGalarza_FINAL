package com.galarza.gestorlogistica.repository;

import com.galarza.gestorlogistica.model.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Long> {
    List<MovimientoStock> findByUsuarioId(Long usuarioId);
}