package com.ras.tarifas.repository;

import com.ras.tarifas.model.CategoriaTarifaria;
import com.ras.tarifas.model.CategoriaConsumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para a entidade {@link CategoriaTarifaria}.
 */
@Repository
public interface CategoriaTarifariaRepository extends JpaRepository<CategoriaTarifaria, Long> {

    /**
     * Busca uma categoria tarifária específica dentro de uma tabela.
     * @param tabelaId  ID da tabela tarifária
     * @param categoria tipo de categoria do consumidor
     * @return Optional com a categoria encontrada, ou vazio se não existir
     */
    Optional<CategoriaTarifaria> findByTabelaTarifariaIdAndCategoria(
            Long tabelaId,
            CategoriaConsumidor categoria
    );
}
