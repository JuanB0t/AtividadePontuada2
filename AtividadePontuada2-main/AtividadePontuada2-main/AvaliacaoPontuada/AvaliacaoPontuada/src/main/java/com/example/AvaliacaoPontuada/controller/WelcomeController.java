package com.example.AvaliacaoPontuada.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> boasVindas() {
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Bem-vindo à nossa API REST com Spring Boot!");
        return ResponseEntity.ok(response);
    }
}