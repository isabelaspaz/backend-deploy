package com.avaliador.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliador.dto.FilmeDTO;
import com.avaliador.entity.Filme;
import com.avaliador.entity.Usuario;
import com.avaliador.repository.FilmeRepository;
import com.avaliador.repository.UsuarioRepository;
import com.avaliador.service.FilmeService;

@Service
public class FilmeServiceImpl implements FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public FilmeDTO cadastrarFilme(FilmeDTO dto, byte[] posterBytes) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Filme filme = new Filme(
                null,
                dto.getTituloFilme(),
                dto.getDiretorFilme(),
                dto.getAnoFilme(),
                dto.getGeneroFilme(),
                posterBytes != null ? Base64.getEncoder().encodeToString(posterBytes) : null,
                dto.getAvaliacaoFilme(),
                dto.getNotaFilme(),
                dto.getStatusFilme(),
                usuario
        );

        Filme salvo = filmeRepository.save(filme);
        return toDTO(salvo);
    }

    public List<FilmeDTO> listarFilmesPorUsuario(Long idUsuario) {
        return filmeRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmeDTO atualizarFilme(Long id, FilmeDTO dto) {
        Optional<Filme> opt = filmeRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        Filme filme = opt.get();
        filme.setTituloFilme(dto.getTituloFilme());
        filme.setDiretorFilme(dto.getDiretorFilme());
        filme.setAnoFilme(dto.getAnoFilme());
        filme.setGeneroFilme(dto.getGeneroFilme());
        filme.setAvaliacaoFilme(dto.getAvaliacaoFilme());
        filme.setNotaFilme(dto.getNotaFilme());
        filme.setStatusFilme(dto.getStatusFilme());
        filme.setPosterFilme(dto.getPosterFilme());

        return toDTO(filmeRepository.save(filme));
    }

    @Override
    public boolean excluirFilmePorId(Long id) {
        if (!filmeRepository.existsById(id)) {
            return false;
        }
        filmeRepository.deleteById(id);
        return true;
    }

    private FilmeDTO toDTO(Filme filme) {
        return new FilmeDTO(
                filme.getIdFilme(),
                filme.getTituloFilme(),
                filme.getDiretorFilme(),
                filme.getAnoFilme(),
                filme.getGeneroFilme(),
                filme.getAvaliacaoFilme(),
                filme.getNotaFilme(),
                filme.getStatusFilme(),
                filme.getPosterFilme(),
                filme.getUsuario().getIdUsuario()
        );
    }
}
