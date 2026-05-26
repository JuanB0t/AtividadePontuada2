package com.example.AvaliacaoPontuada.repository;

import com.example.AvaliacaoPontuada.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    boolean existsByEmail(String email);
}