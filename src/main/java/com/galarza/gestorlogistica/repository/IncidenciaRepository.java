package com.galarza.gestorlogistica.repository;

import com.galarza.gestorlogistica.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByUsuarioId(Long usuarioId);
}