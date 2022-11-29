package br.com.alura.comex.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public record ClienteDTO(
        @JsonProperty String nome,
        @JsonProperty String CPF,
        @JsonProperty String telefone,
        @JsonProperty String local) {

    public ClienteDTO(String nome, String CPF, String telefone, String local) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.local = local;
    }
}
