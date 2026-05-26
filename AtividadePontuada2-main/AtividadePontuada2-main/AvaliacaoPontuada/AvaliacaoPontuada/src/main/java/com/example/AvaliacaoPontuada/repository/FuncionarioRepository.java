package com.example.AvaliacaoPontuada.repository;

import com.example.AvaliacaoPontuada.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
    boolean existsByemail(String email);

    boolean existsByEmail(String email);
}