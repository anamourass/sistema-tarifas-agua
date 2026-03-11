package com.ras.tarifas.repository;

import com.ras.tarifas.model.FaixaConsumo;
import com.ras.tarifas.model.CategoriaConsumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaixaConsumoRepository extends JpaRepository<FaixaConsumo, Long> {

    @Query("""
SELECT f FROM FaixaConsumo f
JOIN f.categoriaTarifaria c
WHERE c.categoria = :categoria
ORDER BY f.consumoMinimo
""")
    List<FaixaConsumo> buscarPorCategoria(CategoriaConsumidor categoria);

}