package com.ras.tarifas.dto;

import java.math.BigDecimal;

public class DetalhamentoDTO {

    private Integer inicio;

    private Integer fim;

    private Integer m3Cobrados;

    private BigDecimal valorUnitario;

    private BigDecimal subtotal;

    public DetalhamentoDTO() {
    }

    public DetalhamentoDTO(Integer inicio,
                           Integer fim,
                           Integer m3Cobrados,
                           BigDecimal valorUnitario,
                           BigDecimal subtotal) {

        this.inicio = inicio;
        this.fim = fim;
        this.m3Cobrados = m3Cobrados;
        this.valorUnitario = valorUnitario;
        this.subtotal = subtotal;
    }

    public Integer getInicio() {
        return inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public Integer getM3Cobrados() {
        return m3Cobrados;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

}

