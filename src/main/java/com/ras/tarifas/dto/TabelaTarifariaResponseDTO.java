package com.ras.tarifas.dto;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

//@Entity
//@Table(name = "tabela_tarifaria")
public class TabelaTarifariaResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private LocalDate dataVigenciaInicio;

    private LocalDate dataVigenciaFim;

    public TabelaTarifariaResponseDTO() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVigenciaInicio() {
        return dataVigenciaInicio;
    }

    public void setDataVigenciaInicio(LocalDate dataVigenciaInicio) {
        this.dataVigenciaInicio = dataVigenciaInicio;
    }

    public LocalDate getDataVigenciaFim() {
        return dataVigenciaFim;
    }

    public void setDataVigenciaFim(LocalDate dataVigenciaFim) {
        this.dataVigenciaFim = dataVigenciaFim;
    }
}