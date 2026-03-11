package com.ras.tarifas.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidade principal que representa uma Tabela Tarifária completa.
 *
 * Uma tabela tarifária agrupa todas as categorias de consumidores e suas
 * respectivas faixas de consumo. Pode ter um período de vigência definido,
 * permitindo que múltiplas tabelas coexistam no sistema para fins históricos
 * ou de planejamento futuro.
 *
 * Mapeamento relacional:
 *
 *   tabela_tarifaria (1) ----< (N) categoria_tarifaria (1) ----< (N) faixa_consumo
 *
 */
@Entity
public class TabelaTarifaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private LocalDate dataVigencia;

    @OneToMany(mappedBy = "tabelaTarifaria")
    private List<CategoriaTarifaria> categorias;

    public TabelaTarifaria() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(LocalDate dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public List<CategoriaTarifaria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaTarifaria> categorias) {
        this.categorias = categorias;
    }
}
