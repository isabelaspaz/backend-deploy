package com.avaliador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaliador.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmailUsuario(String emailUsuario);


}
