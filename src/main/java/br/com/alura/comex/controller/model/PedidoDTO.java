package br.com.alura.comex.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PedidoDTO(
        @JsonProperty Long idCliente,
        @JsonProperty List<ItemProdutoDTO> itemProdutos) {

    public Long getIdCliente() {
        return idCliente;
    }

    public List<ItemProdutoDTO> getItemProdutos() {
        return itemProdutos;
    }
}
