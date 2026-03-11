package com.ras.tarifas.model;

import com.ras.tarifas.dto.FaixaConsumoDTO;
import com.ras.tarifas.exception.RegraDeNegocioException;

import java.util.List;

/**
 * Serviço responsável por validar faixas de consumo.
 */
public class ValidacaoFaixasService {

    /**
     * Valida se a lista de faixas está correta:
     * - Começa em 0
     * - Não possui lacunas
     * - Não possui sobreposições
     * - Cada faixa tem início menor que o fim
     *
      */
    public void validar(List<FaixaConsumoDTO> faixas) {
        if (faixas == null || faixas.isEmpty()) {
            throw new RegraDeNegocioException("Lista de faixas vazia");
        }

        int esperado = 0;
        for (FaixaConsumoDTO faixa : faixas) {
            if (faixa.getInicio() != esperado) {
                throw new RegraDeNegocioException(
                        "Faixas devem iniciar em " + esperado
                );
            }

            if (faixa.getInicio() >= faixa.getFim()) {
                throw new RegraDeNegocioException(
                        "Início da faixa deve ser menor que o fim"
                );
            }

            esperado = faixa.getFim() + 1; // próximo início esperado
        }
    }
}