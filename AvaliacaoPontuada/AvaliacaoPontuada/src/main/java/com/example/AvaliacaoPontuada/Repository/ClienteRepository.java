package com.example.AvaliacaoPontuada.Repository;

import com.example.AvaliacaoPontuada.Model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository
            extends JpaRepository<ClienteModel, Long> {
    Optional<ClienteModel> findByEmail(String email);
}
