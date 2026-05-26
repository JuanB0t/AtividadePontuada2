package com.example.AvaliacaoPontuada.controller;

import com.example.AvaliacaoPontuada.dto.FuncionarioRequestDto;
import com.example.AvaliacaoPontuada.dto.FuncionarioResponseDto;
import com.example.AvaliacaoPontuada.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> criar(@RequestBody FuncionarioRequestDto request) {
        service.salvar(request);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Cadastrado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDto>> listar() {
        List<FuncionarioResponseDto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> obterPorId(@PathVariable Long id) {
        FuncionarioResponseDto dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> atualizar(@PathVariable Long id, @RequestBody FuncionarioRequestDto request) {
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