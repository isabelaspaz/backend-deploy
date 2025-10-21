package com.avaliador.service;

import java.util.List;

import com.avaliador.dto.FilmeDTO;
import com.avaliador.entity.Filme;

public interface FilmeService {

    FilmeDTO cadastrarFilme(FilmeDTO dto, byte[] posterBytes);

    List<FilmeDTO> listarFilmesPorUsuario(Long idUsuario);

    FilmeDTO atualizarFilme(Long id, FilmeDTO dto);

    boolean excluirFilmePorId(Long id);

    

    
}
