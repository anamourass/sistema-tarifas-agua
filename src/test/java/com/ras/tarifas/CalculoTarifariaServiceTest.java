package com.ras.tarifas.dto;

import java.math.BigDecimal;

public class FaixaConsumoDTO {

    private Integer inicio;
    private Integer fim;
    private BigDecimal valorUnitario;

    public FaixaConsumoDTO() {
    }

    public FaixaConsumoDTO(Integer inicio, Integer fim, BigDecimal valorUnitario) {
        this.inicio = inicio;
        this.fim = fim;
        this.valorUnitario = valorUnitario;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public void setFim(Integer fim) {
        this.fim = fim;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    // Builder manual
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer inicio;
        private Integer fim;
        private BigDecimal valorUnitario;

        public Builder inicio(Integer inicio) {
            this.inicio = inicio;
            return this;
        }

        public Builder fim(Integer fim) {
            this.fim = fim;
            return this;
        }

        public Builder valorUnitario(BigDecimal valorUnitario) {
            this.valorUnitario = valorUnitario;
            return this;
        }

        public FaixaConsumoDTO build() {
            return new FaixaConsumoDTO(inicio, fim, valorUnitario);
        }
    }
}