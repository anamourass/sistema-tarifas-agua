package com.ras.tarifas.controller;

import com.ras.tarifas.dto.CalculoRequestDTO;
import com.ras.tarifas.dto.CalculoResponseDTO;
import com.ras.tarifas.service.CalculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculos")
public class CalculoController {

    private final CalculoService calculoService;

    public CalculoController(CalculoService calculoService) {
        this.calculoService = calculoService;
    }

    @PostMapping
    public ResponseEntity<CalculoResponseDTO> calcular(
            @RequestBody CalculoRequestDTO request) {

        CalculoResponseDTO response =
                calculoService.calcular(request);

        return ResponseEntity.ok(response);
    }
}