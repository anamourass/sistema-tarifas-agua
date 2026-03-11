package com.ras.tarifas.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

public class TabelaTarifariaRequestDTO {

    @NotBlank(message = "O nome da tabela tarifária é obrigatório")
    private String nome;

    private String descricao;

    private LocalDate dataVigenciaInicio;

    private LocalDate dataVigenciaFim;

    @NotEmpty(message = "A tabela deve ter pelo menos uma categoria")
    @Valid
    private List<CategoriaTarifariaDTO> categorias;

    public TabelaTarifariaRequestDTO() {
    }

    public TabelaTarifariaRequestDTO(String nome, String descricao,
                                     LocalDate dataVigenciaInicio,
                                     LocalDate dataVigenciaFim,
                                     List<CategoriaTarifariaDTO> categorias) {

        this.nome = nome;
        this.descricao = descricao;
        this.dataVigenciaInicio = dataVigenciaInicio;
        this.dataVigenciaFim = dataVigenciaFim;
        this.categorias = categorias;
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

    public List<CategoriaTarifariaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaTarifariaDTO> categorias) {
        this.categorias = categorias;
    }
}