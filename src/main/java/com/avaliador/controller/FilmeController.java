package com.avaliador.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.avaliador.dto.FilmeDTO;
import com.avaliador.service.FilmeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private static final Logger logger = LoggerFactory.getLogger(FilmeController.class);

    @Autowired
    private FilmeService filmeService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmeDTO> cadastrarFilme(
            @RequestPart("filme") String filmeJson,
            @RequestPart(value = "poster", required = false) MultipartFile posterFile) {

        try {
            logger.info("Recebido JSON do filme: {}", filmeJson);
            if (posterFile != null) {
                logger.info("Recebido poster: {}", posterFile.getOriginalFilename());
            } else {
                logger.info("Nenhum poster recebido.");
            }

            ObjectMapper mapper = new ObjectMapper();
            FilmeDTO dto = mapper.readValue(filmeJson, FilmeDTO.class);
            logger.info("Objeto FilmeDTO desserializado: {}", dto);

            byte[] posterBytes = (posterFile != null) ? posterFile.getBytes() : null;
            FilmeDTO salvo = filmeService.cadastrarFilme(dto, posterBytes);

            logger.info("Filme salvo com sucesso: {}", salvo);
            return ResponseEntity.ok(salvo);

        } catch (IOException e) {
            logger.error("Erro de IO ao cadastrar filme: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Erro ao cadastrar filme: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarFilmes(@RequestParam Long idUsuario) {
        logger.info("Listando filmes do usuário com id: {}", idUsuario);
        List<FilmeDTO> filmes = filmeService.listarFilmesPorUsuario(idUsuario);
        if (filmes.isEmpty()) {
            logger.info("Nenhum filme encontrado para o usuário id {}", idUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFilme(@PathVariable Long id, @RequestBody FilmeDTO filmeDTO) {
        logger.info("Atualizando filme id: {}", id);
        FilmeDTO atualizado = filmeService.atualizarFilme(id, filmeDTO);
        if (atualizado == null) {
            logger.warn("Filme não encontrado para atualização, id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado para atualização");
        }
        logger.info("Filme atualizado com sucesso: {}", atualizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> excluirFilmePorId(@PathVariable Long id) {
        logger.info("Excluindo filme por id: {}", id);
        boolean excluido = filmeService.excluirFilmePorId(id);
        if (excluido) {
            logger.info("Filme com ID {} excluído com sucesso.", id);
            return ResponseEntity.ok("Filme com ID " + id + " excluído com sucesso.");
        } else {
            logger.warn("Filme com ID {} não encontrado para exclusão.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme com ID " + id + " não encontrado.");
        }
    }

}
