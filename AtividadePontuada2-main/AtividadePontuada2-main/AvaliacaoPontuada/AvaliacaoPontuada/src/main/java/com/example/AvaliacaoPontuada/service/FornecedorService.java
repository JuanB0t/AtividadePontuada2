package com.example.AvaliacaoPontuada.service;

import com.example.AvaliacaoPontuada.dto.FornecedorRequestDto;
import com.example.AvaliacaoPontuada.dto.FornecedorResponseDto;
import com.example.AvaliacaoPontuada.exception.EmailJaCadastradoException;
import com.example.AvaliacaoPontuada.exception.ResourceNotFoundException;
import com.example.AvaliacaoPontuada.model.FornecedorModel;
import com.example.AvaliacaoPontuada.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public FornecedorResponseDto salvar(FornecedorRequestDto request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("Fornecedor já cadastrado.");
        }

        FornecedorModel model = new FornecedorModel();
        model.setNome(request.getNome());
        model.setCnpj(request.getCnpj());
        model.setEmail(request.getEmail());
        model.setTelefone(request.getTelefone());

        FornecedorModel salvo = repository.save(model);
        return converterParaResponse(salvo);
    }

    public List<FornecedorResponseDto> listarTodos() {
        List<FornecedorModel> lista = repository.findAll();
        List<FornecedorResponseDto> listaDto = new ArrayList<>();
        for (FornecedorModel f : lista) {
            listaDto.add(converterParaResponse(f));
        }
        return listaDto;
    }

    public FornecedorResponseDto buscarPorId(Long id) {
        FornecedorModel f = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado."));
        return converterParaResponse(f);
    }

    public FornecedorResponseDto atualizar(Long id, FornecedorRequestDto request) {
        FornecedorModel f = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado."));

        if (!f.getEmail().equals(request.getEmail()) && repository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("Fornecedor já cadastrado.");
        }

        f.setNome(request.getNome());
        f.setCnpj(request.getCnpj());
        f.setEmail(request.getEmail());
        f.setTelefone(request.getTelefone());

        FornecedorModel atualizado = repository.save(f);
        return converterParaResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Fornecedor não encontrado.");
        }
        repository.deleteById(id);
    }

    private FornecedorResponseDto converterParaResponse(FornecedorModel f) {
        return new FornecedorResponseDto(f.getNome(), f.getEmail(), f.getTelefone());
    }
}