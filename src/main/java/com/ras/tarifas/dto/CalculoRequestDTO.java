package com.ras.tarifas.dto;

import com.ras.tarifas.model.CategoriaConsumidor;

public class CalculoRequestDTO {

    private CategoriaConsumidor categoria;

    private Integer consumo;

    public CalculoRequestDTO() {
    }

    public CategoriaConsumidor getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaConsumidor categoria) {
        this.categoria = categoria;
    }

    public Integer getConsumo() {
        return consumo;
    }

    public void setConsumo(Integer consumo) {
        this.consumo = consumo;
    }

    public void setConsumo(Long consumo) {

    }
}

