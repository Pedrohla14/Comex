package br.com.alura.comex.controller.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginForm(
        String email,
        String senha) {

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
