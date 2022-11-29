package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty
	@Enumerated(EnumType.STRING)
	private TipoPerfil tipoPerfil = TipoPerfil.COMUM;

	@Override
	public String getAuthority() {
		return tipoPerfil.toString();
	}
}
