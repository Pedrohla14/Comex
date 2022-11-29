package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @JsonProperty
    @Length(min = 2)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @JsonProperty
    @DecimalMin("0.01")
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @NotNull
    @JsonProperty
    @Min(value = 0)
    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @NotNull
    @JsonProperty
    @ManyToOne(optional = false)
    private Categoria categoria;

    public Produto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Produto setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
        return this;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}