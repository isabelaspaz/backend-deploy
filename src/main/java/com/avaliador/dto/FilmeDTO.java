package com.avaliador.dto;

import com.avaliador.enums.StatusFilme;

public class FilmeDTO {

    private Long idFilme;
    private String tituloFilme;
    private String diretorFilme;
    private Integer anoFilme;
    private String generoFilme;
    private String avaliacaoFilme;
    private Double notaFilme;
    private StatusFilme statusFilme;
    private String posterFilme;
    private Long idUsuario;

    public FilmeDTO(Long idFilme, String tituloFilme, String diretorFilme, Integer anoFilme, String generoFilme,
            String avaliacaoFilme, Double notaFilme, StatusFilme statusFilme, String posterFilme, Long idUsuario) {
        this.idFilme = idFilme;
        this.tituloFilme = tituloFilme;
        this.diretorFilme = diretorFilme;
        this.anoFilme = anoFilme;
        this.generoFilme = generoFilme;
        this.avaliacaoFilme = avaliacaoFilme;
        this.notaFilme = notaFilme;
        this.statusFilme = statusFilme;
        this.posterFilme = posterFilme;
        this.idUsuario = idUsuario;
    }

    public FilmeDTO(String tituloFilme, Double notaFilme, String avaliacaoFilme, StatusFilme statusFilme, Long idUsuario) {
        this.tituloFilme = tituloFilme;
        this.notaFilme = notaFilme;
        this.avaliacaoFilme = avaliacaoFilme;
        this.statusFilme = statusFilme;
        this.idUsuario = idUsuario;
    }

    public FilmeDTO() {
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getDiretorFilme() {
        return diretorFilme;
    }

    public void setDiretorFilme(String diretorFilme) {
        this.diretorFilme = diretorFilme;
    }

    public Integer getAnoFilme() {
        return anoFilme;
    }

    public void setAnoFilme(Integer anoFilme) {
        this.anoFilme = anoFilme;
    }

    public String getGeneroFilme() {
        return generoFilme;
    }

    public void setGeneroFilme(String generoFilme) {
        this.generoFilme = generoFilme;
    }

    public String getAvaliacaoFilme() {
        return avaliacaoFilme;
    }

    public void setAvaliacaoFilme(String avaliacaoFilme) {
        this.avaliacaoFilme = avaliacaoFilme;
    }

    public Double getNotaFilme() {
        return notaFilme;
    }

    public void setNotaFilme(Double notaFilme) {
        this.notaFilme = notaFilme;
    }

    public StatusFilme getStatusFilme() {
        return statusFilme;
    }

    public void setStatusFilme(StatusFilme statusFilme) {
        this.statusFilme = statusFilme;
    }

    public String getPosterFilme() {
        return posterFilme;
    }

    public void setPosterFilme(String posterFilme) {
        this.posterFilme = posterFilme;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
