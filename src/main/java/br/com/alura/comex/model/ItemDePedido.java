package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemDePedido {

    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @JsonProperty
    @Column(nullable = false)
    private Integer quantidade;

    @JsonProperty
    @ManyToOne(optional = false)
    private Pedido pedido;

    @JsonProperty
    @ManyToOne(optional = false)
    private Produto produto;

    @JsonProperty
    @Column(nullable = false)
    private BigDecimal desconto = BigDecimal.ZERO;

    @JsonProperty
    @Column(nullable = false)
    private BigDecimal valorComDesconto = BigDecimal.ZERO;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private TipoDescontoItem tipoDesconto = TipoDescontoItem.NENHUM;

    public ItemDePedido() {
    }

    public ItemDePedido(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoUnitario = produto.getPrecoUnitario();
        this.tipoDesconto = verificarTipoDesconto(quantidade);
        this.desconto = calcularDesconto(quantidade, produto);
        this.valorComDesconto = calcularValorComDesconto(quantidade, produto);
    }

    private static TipoDescontoItem verificarTipoDesconto(Integer quantidade) {
        return quantidade > 10 ? TipoDescontoItem.QUANTIDADE : TipoDescontoItem.NENHUM;
    }

    public static BigDecimal calcularDesconto(Integer quantidade, Produto produto) {
        return quantidade > 10 ? produto.getPrecoUnitario()
                .subtract((produto.getPrecoUnitario().multiply(new BigDecimal("0.9"))))
                .multiply(new BigDecimal(quantidade.toString())) : BigDecimal.ZERO;
    }

    public static BigDecimal calcularValorComDesconto(Integer quantidade, Produto produto) {
        return quantidade > 10 ? produto.getPrecoUnitario()
                .subtract((produto.getPrecoUnitario().multiply(new BigDecimal("0.1"))))
                .multiply(new BigDecimal(quantidade.toString())) : produto.getPrecoUnitario()
                .multiply(new BigDecimal(quantidade.toString()));
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getValorComDesconto() {
        return valorComDesconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }
}
