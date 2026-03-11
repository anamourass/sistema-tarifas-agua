package com.ras.tarifas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<String> tratarRegraNegocio(RegraDeNegocioException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErroGenerico(Exception ex){

        return ResponseEntity.internalServerError().body("Erro interno");

    }

}