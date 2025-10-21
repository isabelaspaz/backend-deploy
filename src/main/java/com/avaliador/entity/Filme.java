package com.avaliador.entity;

import com.avaliador.enums.StatusFilme;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilme;

    private String tituloFilme;
    private String diretorFilme;
    private Integer anoFilme;
    private String generoFilme;

    @Lob
    private String posterFilme;

    @Lob
    private String avaliacaoFilme;
    private Double notaFilme;

    @Enumerated(EnumType.STRING)
    private StatusFilme statusFilme;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Filme() {
    }

    public Filme(Long idFilme, String tituloFilme, String diretorFilme, Integer anoFilme, String generoFilme,
            String posterFilme, String avaliacaoFilme, Double notaFilme, StatusFilme statusFilme,
            Usuario usuario) {
        this.idFilme = idFilme;
        this.tituloFilme = tituloFilme;
        this.diretorFilme = diretorFilme;
        this.anoFilme = anoFilme;
        this.generoFilme = generoFilme;
        this.posterFilme = posterFilme;
        this.avaliacaoFilme = avaliacaoFilme;
        this.notaFilme = notaFilme;
        this.statusFilme = statusFilme;
        this.usuario = usuario;
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

    public String getPosterFilme() {
        return posterFilme;
    }

    public void setPosterFilme(String posterFilme) {
        this.posterFilme = posterFilme;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
