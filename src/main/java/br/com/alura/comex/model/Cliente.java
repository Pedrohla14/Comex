package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonProperty
    private Usuario usuario;

    @Column
    @NotNull
    @NotEmpty
    @JsonProperty
    @Size(min = 2)
    private String nome;

    @Column
    @NotNull
    @JsonProperty
    private String cpf;

    @Column
    @NotNull
    @JsonProperty
    private String telefone;

    @Column
    @NotNull
    @JsonProperty
    private String rua;

    @Column
    @JsonProperty
    @Pattern(regexp = "^[A-Za-z0-9 /]*$")
    private String numero;

    @Column
    @JsonProperty
    private String complemento;

    @NotNull
    @Column
    @JsonProperty
    private String bairro;

    @NotNull
    @Column
    @JsonProperty
    private String cidade;

    @Column
    @NotNull
    @JsonProperty
    private String estado;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @PrePersist
    private void validateNumero() {
        if (this.numero.isBlank()) {
            this.numero = "S/N";
        }
    }
}
