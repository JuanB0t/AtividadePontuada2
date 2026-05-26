package com.example.AvaliacaoPontuada.service;

import com.example.AvaliacaoPontuada.dto.ClienteRequestDto;
import com.example.AvaliacaoPontuada.dto.ClienteResponseDto;
import com.example.AvaliacaoPontuada.exception.EmailJaCadastradoException;
import com.example.AvaliacaoPontuada.exception.ResourceNotFoundException;
import com.example.AvaliacaoPontuada.model.ClienteModel;
import com.example.AvaliacaoPontuada.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteResponseDto salvar(ClienteRequestDto request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("Cliente já cadastrado.");
        }

        ClienteModel model = new ClienteModel();
        model.setNome(request.getNome());
        model.setCpf(request.getCpf());
        model.setDataNascimento(request.getDataNascimento());
        model.setEmail(request.getEmail());
        model.setSenha(request.getSenha());

        ClienteModel salvo = repository.save(model);
        return converterParaResponse(salvo);
    }

    public List<ClienteResponseDto> listarTodos() {
        List<ClienteModel> lista = repository.findAll();
        List<ClienteResponseDto> listaDto = new ArrayList<>();
        for (ClienteModel c : lista) {
            listaDto.add(converterParaResponse(c));
        }
        return listaDto;
    }

    public ClienteResponseDto buscarPorId(Long id) {
        ClienteModel c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
        return converterParaResponse(c);
    }

    public ClienteResponseDto atualizar(Long id, ClienteRequestDto request) {
        ClienteModel c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        if (!c.getEmail().equals(request.getEmail()) && repository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("Cliente já cadastrado.");
        }

        c.setNome(request.getNome());
        c.setCpf(request.getCpf());
        c.setDataNascimento(request.getDataNascimento());
        c.setEmail(request.getEmail());
        c.setSenha(request.getSenha());

        ClienteModel atualizado = repository.save(c);
        return converterParaResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado.");
        }
        repository.deleteById(id);
    }

    private ClienteResponseDto converterParaResponse(ClienteModel c) {
        return new ClienteResponseDto(c.getNome(), c.getDataNascimento(), c.getEmail());
    }
}