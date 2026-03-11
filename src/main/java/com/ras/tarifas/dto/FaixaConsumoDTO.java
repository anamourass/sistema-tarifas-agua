package com.ras.tarifas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class FaixaConsumoDTO {

    private Long id;

    @NotNull(message = "O início da faixa é obrigatório")
    @Min(value = 0, message = "O início da faixa não pode ser negativo")
    private Integer inicio;

    @NotNull(message = "O fim da faixa é obrigatório")
    @Min(value = 1, message = "O fim da faixa deve ser maior que zero")
    private Integer fim;

    @NotNull(message = "O valor unitário é obrigatório")
    @DecimalMin(value = "0.0001", message = "O valor unitário deve ser maior que zero")
    private BigDecimal valorUnitario;

    private CategoriaTarifariaDTO categoriaTarifariaDto;

    // Construtor vazio
    public FaixaConsumoDTO() {}

    // Construtor completo
    public FaixaConsumoDTO(Long id, Integer inicio, Integer fim, BigDecimal valorUnitario, CategoriaTarifariaDTO categoriaTarifariaDto) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.valorUnitario = valorUnitario;
        this.categoriaTarifariaDto = categoriaTarifariaDto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getInicio() { return inicio; }
    public void setInicio(Integer inicio) { this.inicio = inicio; }

    public Integer getFim() { return fim; }
    public void setFim(Integer fim) { this.fim = fim; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public BigDecimal getValorM3() {
        return null;
    }

    public CategoriaTarifariaDTO getCategoriaTarifariaDto() {
        return categoriaTarifariaDto;
    }

    public void setCategoriaTarifariaDto(CategoriaTarifariaDTO categoriaTarifariaDto) {
        this.categoriaTarifariaDto = categoriaTarifariaDto;
    }
}