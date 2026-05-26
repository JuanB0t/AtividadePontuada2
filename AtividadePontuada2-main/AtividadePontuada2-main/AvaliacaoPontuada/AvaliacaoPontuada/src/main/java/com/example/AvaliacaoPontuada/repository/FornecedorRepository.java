package com.example.AvaliacaoPontuada.repository;

import com.example.AvaliacaoPontuada.model.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel, Long> {
    boolean existsByEmail(String email);
}