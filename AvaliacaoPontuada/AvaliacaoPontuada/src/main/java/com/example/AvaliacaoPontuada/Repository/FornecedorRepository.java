package com.example.AvaliacaoPontuada.Repository;

import com.example.AvaliacaoPontuada.Model.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository
                extends JpaRepository<FornecedorModel, Long>{
    Optional<FornecedorModel> findByEmail(String email);

}
