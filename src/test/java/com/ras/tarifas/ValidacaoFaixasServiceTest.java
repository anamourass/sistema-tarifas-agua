package com.ras.tarifas;

import com.ras.tarifas.dto.FaixaConsumoDTO;
import com.ras.tarifas.exception.RegraDeNegocioException;

import java.util.Comparator;
import java.util.List;

/**
 * Serviço de validação de faixas de consumo.
 */
public class ValidacaoFaixasService {

    /**
     * Valida se a lista de faixas é contígua, não sobreposta e começa em zero.
     * @param faixas lista de faixas de consumo
     * @throws RegraDeNegocioException se houver algum problema de validação
     */
    public void validar(List<FaixaConsumoDTO> faixas) {
        if (faixas == null || faixas.isEmpty()) {
            throw new RegraDeNegocioException("Lista de faixas não pode ser vazia");
        }

        // Ordena as faixas pelo início
        faixas.sort(Comparator.comparingInt(FaixaConsumoDTO::getInicio));

        int esperadoInicio = 0;
        for (FaixaConsumoDTO faixa : faixas) {

            if (faixa.getInicio() != esperadoInicio) {
                throw new RegraDeNegocioException(
                        "Faixa inválida: deve iniciar em " + esperadoInicio);
            }

            if (faixa.getFim() <= faixa.getInicio()) {
                throw new RegraDeNegocioException(
                        "Faixa inválida: fim deve ser maior que o início");
            }

            // Atualiza o próximo início esperado
            esperadoInicio = faixa.getFim() + 1;
        }

        // Verificação de sobreposição
        for (int i = 1; i < faixas.size(); i++) {
            FaixaConsumoDTO anterior = faixas.get(i - 1);
            FaixaConsumoDTO atual = faixas.get(i);
            if (atual.getInicio() <= anterior.getFim()) {
                throw new RegraDeNegocioException("Sobreposição de faixas detectada");
            }
        }
    }
}