package com.avaliador.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliador.dto.UsuarioDTO;
import com.avaliador.entity.Usuario;
import com.avaliador.repository.UsuarioRepository;
import com.avaliador.service.UsuarioService;
import com.avaliador.util.HashUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO) {
        System.out.println(">>> Iniciando cadastro");

        if (usuarioRepository.findByEmailUsuario(usuarioDTO.getEmailUsuario()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado, por favor, tente outro.");
        }

        System.out.println(">>> Email verificado");
        String senhaHash = HashUtil.gerarHashSHA256(usuarioDTO.getSenhaUsuario());

        Usuario usuario = new Usuario(
                usuarioDTO.getNomeUsuario(),
                usuarioDTO.getEmailUsuario(),
                senhaHash);

        System.out.println(">>> Salvando usuário: " + usuario.getNomeUsuario() + ", " + usuario.getEmailUsuario());

        Usuario salvo = usuarioRepository.save(usuario); // só essa

        System.out.println(">>> Usuário salvo com id: " + salvo.getIdUsuario());

        return salvo; 
    }


    
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }


    
    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


    
    @Override
    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        usuarioExistente.setNomeUsuario(usuarioDTO.getNomeUsuario());
        usuarioExistente.setEmailUsuario(usuarioDTO.getEmailUsuario());

        if (usuarioDTO.getSenhaUsuario() != null && !usuarioDTO.getSenhaUsuario().isEmpty()) {
            usuarioExistente.setSenhaUsuario(HashUtil.gerarHashSHA256(usuarioDTO.getSenhaUsuario()));
        }
        return usuarioRepository.save(usuarioExistente);
    }


    
    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    
    @Override
    public Usuario validarLogin(String email, String senha) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmailUsuario(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            String senhaHash = HashUtil.gerarHashSHA256(senha);
            if (usuario.getSenhaUsuario().equals(senhaHash)) {
                return usuario;
            }
        }
        return null;
    }
}
