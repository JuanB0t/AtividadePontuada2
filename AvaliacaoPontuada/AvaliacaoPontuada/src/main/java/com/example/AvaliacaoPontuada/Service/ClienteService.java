package com.example.AvaliacaoPontuada.Service;

import com.example.AvaliacaoPontuada.Model.ClienteModel;
import com.example.AvaliacaoPontuada.Repository.ClienteRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;


    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteModel cadastrar(ClienteModel cliente) {
        repository.findByEmail(cliente.getEmail())
                .ifPresent(c -> {
                    throw new EmailjaCadastradeException(
                            "Cliente já cadastrado");

                });
        return repository.save(cliente);
    }

    public List<ClienteModel> listar() {
        return repository.findAll();

    }

    public ClienteModel buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente não encontrado"));

    }

    public ClienteModel atualizar(Long id,
                                  ClienteModel clienteAtualizado) {

        ClienteModel cliente = buscarPorId(id);

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCpf(clienteAtualizado.getNome());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setSenha(clienteAtualizado.getSenha());

        return repository.save(cliente);
    }
    public void deletar(Long id) {
        ClienteModel cliente = buscarPorId(id);

        repository.delete(cliente);
    }
}




