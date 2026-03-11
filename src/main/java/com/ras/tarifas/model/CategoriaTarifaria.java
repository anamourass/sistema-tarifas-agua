package com.ras.tarifas.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa o vínculo entre uma Tabela Tarifária e uma Categoria de Consumidor.
 *
 * <p>Esta entidade é o elo central do modelo relacional:
 * <ul>
 *   <li>Pertence a uma {@link TabelaTarifaria} (N:1)</li>
 *   <li>Possui um tipo de {@link CategoriaConsumidor} (enum)</li>
 *   <li>Contém N {@link FaixaConsumo} associadas (1:N)</li>
 * </ul>
 *
 * <p>A constraint unique garante que não haja duplicidade de categoria dentro
 * de uma mesma tabela tarifária.
 */
@Entity
public class CategoriaTarifaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaConsumidor categoria;

    @ManyToOne
    @JoinColumn(name = "tabela_id")
    private TabelaTarifaria tabelaTarifaria;

    @OneToMany(mappedBy = "categoriaTarifaria", cascade = CascadeType.ALL)
    private List<FaixaConsumo> faixas;

    public CategoriaTarifaria() {}

    public Long getId() {
        return id;
    }

    public CategoriaConsumidor getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaConsumidor categoria) {
        this.categoria = categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TabelaTarifaria getTabelaTarifaria() {
        return tabelaTarifaria;
    }

    public void setTabelaTarifaria(TabelaTarifaria tabelaTarifaria) {
        this.tabelaTarifaria = tabelaTarifaria;
    }

    public List<FaixaConsumo> getFaixas() {
        return faixas;
    }

    public void setFaixas(List<FaixaConsumo> faixas) {
        this.faixas = faixas;
    }
}
