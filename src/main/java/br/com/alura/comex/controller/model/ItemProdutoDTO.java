package br.com.alura.comex.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemProdutoDTO(
        @JsonProperty Long idProduto,
        @JsonProperty Integer quantidade) {

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
