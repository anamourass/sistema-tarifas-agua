package com.ras.tarifas.exception;

/**
 *  Exceção lançada quando uma regra de negócio é violada.
 *  exemplos de uso:
 *  Faixas com sobreposição de intervalos
 *  Primeira faixa não iniciando em zero
 *  Consumo não coberto por nenhuma faixa
 *  Categoria duplicada na mesma tabela
 *
 */
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
