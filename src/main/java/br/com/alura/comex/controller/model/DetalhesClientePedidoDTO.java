package br.com.alura.comex.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DetalhesClientePedidoDTO(
        @JsonProperty Long id,
        @JsonProperty String nome) {
}
