package com.example.AvaliacaoPontuada.Service;


import com.example.AvaliacaoPontuada.Model.FornecedorModel;
import com.example.AvaliacaoPontuada.Repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;


    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public FornecedorModel cadastrar(FornecedorModel fornecedor) {

        repository.findByEmail(fornecedor.getEmail())
                .ifPresent(f -> {
                    throw new EmailJaCadastradoException(
                            "Fornecedor Cadastrado");

                });
        return repository.save(fornecedor);

    }

    public List<FornecedorModel> listar() {
        return repository.findAll();
    }

    public FornecedorModel buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fornecedor não encontrado."));
    }
    public FornecedorModel atualizar(Long id,
                                     FornecedorModel fornecedorAtualizado) {

        FornecedorModel fornecedor = buscarPorId(id);

        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());

        return repository.save(fornecedor);
    }

    // DELETE
    public void deletar(Long id) {

        FornecedorModel fornecedor = buscarPorId(id);

        repository.delete(fornecedor);
    }
}
