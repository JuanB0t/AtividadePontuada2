package com.example.AvaliacaoPontuada.Repository;

import com.example.AvaliacaoPontuada.Model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository
    extends JpaRepository<FuncionarioModel,Long> {
        Optional<FuncionarioModel> findByEmail(String email);
    }


