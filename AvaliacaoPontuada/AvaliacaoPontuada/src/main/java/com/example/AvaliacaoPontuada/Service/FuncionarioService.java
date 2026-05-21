package com.example.AvaliacaoPontuada.Service;

import com.example.AvaliacaoPontuada.Model.FuncionarioModel;
import com.example.AvaliacaoPontuada.Repository.FuncionarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository){
        this.repository = repository;
    }
    public FuncionarioModel  cadastrar(FuncionarioModel  funcionario) {
        repository.findByEmail(funcionario.getEmail())
                .ifPresent(f ->{
                    throw new EmailJaCadastradoException(
                            "Funcionário já cadastrado.");

                });
        return repository.save(funcionario);

    }
    public List<FuncionarioModel> listar(){
        return repository.findAll();

    }
    public FuncionarioModel buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Funcionário não encontrado."));

    }
    public FuncionarioModel  atualiza(Long id, FuncionarioModel funcionarioAtualizado){

        FuncionarioModel  funcionario = buscarPorId(id);

        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setCpf(funcionarioAtualizado.getCpf());
        funcionario.setEmail(funcionarioAtualizado.getEmail());
        funcionario.setTelefone(funcionarioAtualizado.getTelefone());
        funcionario.setSetor(funcionarioAtualizado.getSetor());
        funcionario.setSalario(funcionarioAtualizado.getSalario());

        return repository.save(funcionario);

    }
    public void deletar(Long id){
        FuncionarioModel  funcionario = buscarPorId(id);

        repository.delete(funcionario);
    }
}
