package com.galarza.gestorlogistica.service;

import com.galarza.gestorlogistica.model.Usuario;
import com.galarza.gestorlogistica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        String hash = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());
        usuario.setContraseña(hash);
        return usuarioRepository.save(usuario);
    }

    public boolean verificarPassword(String raw, String hashed) {
        return BCrypt.checkpw(raw, hashed);
    }
}