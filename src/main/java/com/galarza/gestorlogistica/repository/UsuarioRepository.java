package com.galarza.gestorlogistica.repository;
import com.galarza.gestorlogistica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombreUsuario(String nombreUsuario);
}