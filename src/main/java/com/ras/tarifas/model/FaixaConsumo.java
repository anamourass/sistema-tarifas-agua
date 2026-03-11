package com.ras.tarifas.model;

import jakarta.persistence.*;

// Marca a classe como uma entidade JPA
@Entity
@Table(name = "faixa_consumo")
public class FaixaConsumo {

    // Chave primária auto-gerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Consumo mínimo da faixa
    @Column(nullable = false)
    private Double consumoMinimo;

    // Consumo máximo da faixa
    @Column(nullable = false)
    private Double consumoMaximo;

    // Valor da tarifa para essa faixa
    @Column(nullable = false)
    private Double valorTarifa;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaTarifaria categoriaTarifaria;

    // Construtor padrão obrigatório pelo JPA
    public FaixaConsumo() {
    }

    // Construtor com todos os campos (exceto ID)
    public FaixaConsumo(Double consumoMinimo, Double consumoMaximo, Double valorTarifa, CategoriaTarifaria categoriaTarifaria ) {
        this.consumoMinimo = consumoMinimo;
        this.consumoMaximo = consumoMaximo;
        this.valorTarifa = valorTarifa;
        this.categoriaTarifaria = categoriaTarifaria;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getConsumoMinimo() {
        return consumoMinimo;
    }

    public void setConsumoMinimo(Double consumoMinimo) {
        this.consumoMinimo = consumoMinimo;
    }

    public Double getConsumoMaximo() {
        return consumoMaximo;
    }

    public void setConsumoMaximo(Double consumoMaximo) {
        this.consumoMaximo = consumoMaximo;
    }

    public Double getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(Double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public CategoriaTarifaria getCategoriaTarifaria() {
        return categoriaTarifaria;
    }

    public void setCategoriaTarifaria(CategoriaTarifaria categoriaTarifaria) {
        this.categoriaTarifaria = categoriaTarifaria;
    }

    // toString para debug
    @Override
    public String toString() {
        return "FaixaConsumo{" +
                "id=" + id +
                ", consumoMinimo=" + consumoMinimo +
                ", consumoMaximo=" + consumoMaximo +
                ", valorTarifa=" + valorTarifa +
                '}';
    }
}