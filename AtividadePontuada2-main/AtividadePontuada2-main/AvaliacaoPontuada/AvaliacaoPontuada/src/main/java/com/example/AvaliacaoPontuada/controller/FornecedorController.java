package com.example.AvaliacaoPontuada.controller;

import com.example.AvaliacaoPontuada.dto.FornecedorRequestDto;
import com.example.AvaliacaoPontuada.dto.FornecedorResponseDto;
import com.example.AvaliacaoPontuada.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> criar(@RequestBody FornecedorRequestDto request) {
        service.salvar(request);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Cadastrado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDto>> listar() {
        List<FornecedorResponseDto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDto> obterPorId(@PathVariable Long id) {
        FornecedorResponseDto dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> atualizar(@PathVariable Long id, @RequestBody FornecedorRequestDto request) {
        service.atualizar(id, request);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Atualizado com sucesso.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluir(@PathVariable Long id) {
        service.deletar(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Excluído com sucesso.");
        return ResponseEntity.ok(response);
    }
}