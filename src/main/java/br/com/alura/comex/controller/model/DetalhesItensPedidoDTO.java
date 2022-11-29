package br.com.alura.comex.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record DetalhesItensPedidoDTO(
        @JsonProperty Long id,
        @JsonProperty String nomeProduto,
        @JsonProperty String categoria,
        @JsonProperty Integer quantidade,
        @JsonProperty BigDecimal precoUnitario,
        @JsonProperty BigDecimal valorTotal,
        @JsonProperty BigDecimal desconto) {
}
