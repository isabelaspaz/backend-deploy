package com.avaliador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaliador.entity.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByTituloFilmeContainingIgnoreCase(String titulo);

    List<Filme> findByUsuario_IdUsuario(Long idUsuario);
}
