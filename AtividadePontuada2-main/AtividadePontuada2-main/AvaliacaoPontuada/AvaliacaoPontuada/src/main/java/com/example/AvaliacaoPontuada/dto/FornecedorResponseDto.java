package com.example.AvaliacaoPontuada.dto;

public class FornecedorResponseDto {

    private String nome;
    private String email;
    private String telefone;

    public FornecedorResponseDto() {
    }

    public FornecedorResponseDto(String nome,
                                 String email,
                                 String telefone) {

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}