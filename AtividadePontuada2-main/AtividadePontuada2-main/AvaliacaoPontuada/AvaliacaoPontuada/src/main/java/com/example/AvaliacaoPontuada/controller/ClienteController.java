package com.example.AvaliacaoPontuada.controller;

import com.example.AvaliacaoPontuada.dto.ClienteRequestDto;
import com.example.AvaliacaoPontuada.dto.ClienteResponseDto;
import com.example.AvaliacaoPontuada.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> criar(@RequestBody ClienteRequestDto request) {
        service.salvar(request);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Cadastrado com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        List<ClienteResponseDto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> obterPorId(@PathVariable Long id) {
        ClienteResponseDto dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDto request) {
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