package br.com.alura.comex.controller.model;

import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhesPedidoDTO(
        @JsonProperty LocalDate data,
        @JsonProperty BigDecimal valor,
        @JsonProperty("desconto") BigDecimal descontoTotal,
        @JsonProperty List<DetalhesItensPedidoDTO> itensPedido,
        @JsonProperty("cliente") DetalhesClientePedidoDTO detalhesClientePedidoDTO) {
    public static br.com.alura.comex.controller.model.DetalhesPedidoDTO getDetalhesPedidoFromPedido(Pedido pedido) {
        return new br.com.alura.comex.controller.model.DetalhesPedidoDTO(pedido.getData(),
                pedido.getTotalPedidoConsiderandoDescontoComFidelidade(),
                pedido.getDesconto(),
                pedido.getItensPedido().stream()
                        .map(i -> new DetalhesItensPedidoDTO(i.getId(), i.getProduto().getDescricao(), i.getProduto().getCategoria().getNome(),
                                i.getQuantidade(), i.getPrecoUnitario(), i.getValorComDesconto(), i.getDesconto())).collect(Collectors.toList()),
                new DetalhesClientePedidoDTO(pedido.getCliente().getId(), pedido.getCliente().getNome()));
    }
}
