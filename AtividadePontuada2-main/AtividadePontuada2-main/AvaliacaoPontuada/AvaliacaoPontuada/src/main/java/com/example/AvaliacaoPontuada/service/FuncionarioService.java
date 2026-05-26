package com.example.AvaliacaoPontuada.service;

import com.example.AvaliacaoPontuada.dto.FuncionarioRequestDto;
import com.example.AvaliacaoPontuada.dto.FuncionarioResponseDto;
import com.example.AvaliacaoPontuada.exception.EmailJaCadastradoException;
import com.example.AvaliacaoPontuada.exception.ResourceNotFoundException;
import com.example.AvaliacaoPontuada.model.FuncionarioModel;
import com.example.AvaliacaoPontuada.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public FuncionarioResponseDto salvar(FuncionarioRequestDto request) {
        if (repository.existsByemail(request.getEmail())) {
            throw new EmailJaCadastradoException("Funcionário já cadastrado.");
        }

        FuncionarioModel model = new FuncionarioModel();
        model.setNome(request.getNome());
        model.setCpf(request.getCpf());
        model.setEmail(request.getEmail());
        model.setTelefone(request.getTelefone());
        model.setSetor(request.getSetor());
        model.setSalario(request.getSalario());

        FuncionarioModel salvo = repository.save(model);
        return converterParaResponse(salvo);
    }

    public List<FuncionarioResponseDto> listarTodos() {
        List<FuncionarioModel> lista = repository.findAll();
        List<FuncionarioResponseDto> listaDto = new ArrayList<>();
        for (FuncionarioModel f : lista) {
            listaDto.add(converterParaResponse(f));
        }
        return listaDto;
    }

    public FuncionarioResponseDto buscarPorId(Long id) {
        FuncionarioModel f = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado."));
        return converterParaResponse(f);
    }

    public FuncionarioResponseDto atualizar(Long id, FuncionarioRequestDto request) {
        FuncionarioModel f = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado."));

        if (!f.getEmail().equals(request.getEmail()) && repository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("Funcionário já cadastrado.");
        }

        f.setNome(request.getNome());
        f.setCpf(request.getCpf());
        f.setEmail(request.getEmail());
        f.setTelefone(request.getTelefone());
        f.setSetor(request.getSetor());
        f.setSalario(request.getSalario());

        FuncionarioModel atualizado = repository.save(f);
        return converterParaResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado.");
        }
        repository.deleteById(id);
    }

    private FuncionarioResponseDto converterParaResponse(FuncionarioModel f) {
        return new FuncionarioResponseDto(f.getNome(), f.getEmail(), f.getTelefone(), f.getSetor(), f.getSalario());
    }
}