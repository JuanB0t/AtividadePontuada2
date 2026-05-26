package com.example.AvaliacaoPontuada.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            ResourceNotFoundException ex) {

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(resposta);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> handleEmail(
            EmailJaCadastradoException ex) {

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }
}