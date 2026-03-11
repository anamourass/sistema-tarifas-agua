package com.ras.tarifas.repository;

import com.ras.tarifas.model.TabelaTarifaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabelaTarifariaRepository extends JpaRepository<TabelaTarifaria, Long> {

    //Object findMaisRecente();
}
/*
 * Repositório JPA para a entidade {@link TabelaTarifaria}.
 * Estende JpaRepository, que já fornece os métodos CRUD básicos:
 *   save(entity): insere ou atualiza
 *   findById(id): busca por ID
 *   findAll(): lista todos
 *   deleteById(id): remove por ID
 *   existsById(id): verifica existência
 */

