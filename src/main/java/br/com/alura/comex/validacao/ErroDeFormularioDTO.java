package br.com.alura.comex.validacao;

import java.util.Date;

public class ErroDeFormularioDTO {

    private Date timestamp;
    private String message;
    private String details;

    public ErroDeFormularioDTO(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
