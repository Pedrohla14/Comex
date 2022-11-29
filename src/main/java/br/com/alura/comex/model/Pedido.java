package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonProperty
    private LocalDate data = LocalDate.now();

    @JsonProperty
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cliente cliente;

    @Column
    @JsonProperty
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto = TipoDesconto.NENHUM;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemDePedido> itensPedido;

    @Transient
    private Long quantidadePedidosCliente;

    public Pedido() {
    }

    public Pedido(Cliente cliente, List<ItemDePedido> listaItemPedido, Long quantidadePedidosCliente) {
        this.cliente = cliente;
        this.itensPedido = listaItemPedido;
        this.quantidadePedidosCliente = quantidadePedidosCliente;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
    }

    public void setQuantidadePedidosCliente(Long quantidadePedidosCliente) {
        this.quantidadePedidosCliente = quantidadePedidosCliente;
    }

    public BigDecimal getTotalPedidoConsiderandoDescontoSemFidelidade() {
        return itensPedido
                .stream()
                .map(ItemDePedido::getValorComDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPedidoConsiderandoDescontoComFidelidade() {
        return getTotalPedidoConsiderandoDescontoSemFidelidade().subtract(desconto);
    }

    @JsonProperty
    public BigDecimal DescontoTotal() {
        return itensPedido
                .stream()
                .map(i -> i.calcularDesconto(i.getQuantidade(), i.getProduto()))
                .reduce(BigDecimal.ZERO, BigDecimal::add).add(desconto);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<ItemDePedido> getItensPedido() {
        return itensPedido;
    }

    @PostPersist
    private void saveItens() {
        itensPedido.forEach(i -> i.setPedido(this));
    }

    @PrePersist
    private void aplicarDescontoCliente() {
        if (quantidadePedidosCliente >= 5) {
            setDesconto(getTotalPedidoConsiderandoDescontoSemFidelidade().multiply(new BigDecimal("0.05")));
            setTipoDesconto(TipoDesconto.FIDELIDADE);
        }
    }
}
