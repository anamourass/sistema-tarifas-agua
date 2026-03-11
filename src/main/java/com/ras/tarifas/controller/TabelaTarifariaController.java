package com.ras.tarifas.controller;

import com.ras.tarifas.model.TabelaTarifaria;
import com.ras.tarifas.service.TabelaTarifariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //diz ao spring que esta classe é uma API REST
@RequestMapping("/api/tabelas-tarifarias") //cria a rota principal http://localhost:8080/api/tabelas-tarifarias
public class TabelaTarifariaController {

    private final TabelaTarifariaService tabelaTarifariaService;

    public TabelaTarifariaController(TabelaTarifariaService tabelaTarifariaService) {
        this.tabelaTarifariaService = tabelaTarifariaService;
    }

    // Criar tabela tarifária
    @PostMapping
    public ResponseEntity<TabelaTarifaria> criar(
            @RequestBody TabelaTarifaria tabelaTarifaria) {

        TabelaTarifaria tabelaSalva =
                tabelaTarifariaService.salvar(tabelaTarifaria);

        return ResponseEntity.ok(tabelaSalva);
    }

    // Listar tabelas
    @GetMapping
    public ResponseEntity<List<TabelaTarifaria>> listar() {

        List<TabelaTarifaria> tabelas =
                tabelaTarifariaService.listarTodas();

        return ResponseEntity.ok(tabelas);
    }

    // Excluir tabela
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        tabelaTarifariaService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
