package com.example.AvaliacaoPontuada.dto;

public class ClienteResponseDto {

    private String nome;
    private String dataNascimento;
    private String email;

    public ClienteResponseDto() {
    }

    public ClienteResponseDto(String nome,
                              String dataNascimento,
                              String email) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }
}