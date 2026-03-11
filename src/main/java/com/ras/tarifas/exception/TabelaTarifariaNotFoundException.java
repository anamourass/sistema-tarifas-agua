package com.ras.tarifas.exception;

/**
 * Exceção lançada quando uma tabela tarifária não é encontrada no banco de dados.
 */
public class TabelaTarifariaNotFoundException extends RuntimeException {

    public TabelaTarifariaNotFoundException(Long id) {
        super("Tabela tarifária não encontrada com ID: " + id);
    }

    public TabelaTarifariaNotFoundException(String mensagem) {
        super(mensagem);
    }
}
