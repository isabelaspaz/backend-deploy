package com.avaliador.service;

import java.util.List;

import com.avaliador.dto.UsuarioDTO;
import com.avaliador.entity.Usuario;

public interface UsuarioService {
    Usuario cadastrarUsuario(UsuarioDTO usuarioDTO);

    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorId(Long id);

    Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void deletarUsuario(Long id);

    Usuario validarLogin(String email, String senha);


}
