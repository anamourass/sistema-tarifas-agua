package com.ras.tarifas.dto;

import com.ras.tarifas.model.CategoriaConsumidor;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO de saída para o endpoint de cálculo tarifário.
 *
 * <p>Retorna o detalhamento completo do cálculo progressivo por faixas,
 * conforme especificado no desafio técnico RAS 2026.
 *
 * <p>Exemplo de resposta:
 * <pre>
 * {
 *   "categoria": "INDUSTRIAL",
 *   "consumoTotal": 18,
 *   "valorTotal": 26.00,
 *   "detalhamento": [
 *     {
 *       "faixa": { "inicio": 0, "fim": 10 },
 *       "m3Cobrados": 10,
 *       "valorUnitario": 1.00,
 *       "subtotal": 10.00
 *     },
 *     {
 *       "faixa": { "inicio": 11, "fim": 20 },
 *       "m3Cobrados": 8,
 *       "valorUnitario": 2.00,
 *       "subtotal": 16.00
 *     }
 *   ]
 * }
 * </pre>
 */
public class CalculoResponseDTO {

    private String categoria;

    private Integer consumoTotal;

    private BigDecimal valorTotal;

    private List<DetalhamentoDTO> detalhamento;

    public CalculoResponseDTO() {
    }

    public CalculoResponseDTO(String categoria,
                              Integer consumoTotal,
                              BigDecimal valorTotal,
                              List<DetalhamentoDTO> detalhamento) {

        this.categoria = categoria;
        this.consumoTotal = consumoTotal;
        this.valorTotal = valorTotal;
        this.detalhamento = detalhamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getConsumoTotal() {
        return consumoTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public List<DetalhamentoDTO> getDetalhamento() {
        return detalhamento;
    }
}