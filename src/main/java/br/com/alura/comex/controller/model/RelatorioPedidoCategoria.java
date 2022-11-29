package br.com.alura.comex.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public record RelatorioPedidoCategoria(
        @JsonProperty String categoria,
        @JsonProperty Long quantidadeProdutosVendidos,
        @JsonProperty BigDecimal montanteVendidoComFidelidade) implements Serializable {
}
