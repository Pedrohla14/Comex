package br.com.alura.comex.validacao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> dataNotFoundExceptionHandling(EntityNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErroDeFormularioDTO(new Date(), exception.getMessage(), request.getDescription(false)), BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationExceptionHandling(DataIntegrityViolationException exception, WebRequest request) {
        return new ResponseEntity<>(new ErroDeFormularioDTO(new Date(), exception.getMostSpecificCause().getMessage(), request.getDescription(false)), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionnHandling(MethodArgumentNotValidException exception, WebRequest request) {
        return new ResponseEntity<>(new ErroDeFormularioDTO(new Date(), String.format("[%s] %s", exception.getBindingResult().getObjectName().toUpperCase(Locale.ROOT), exception.getBindingResult().getFieldError().getDefaultMessage()), request.getDescription(false)), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErroDeFormularioDTO(new Date(), exception.getMessage(), request.getDescription(false)), INTERNAL_SERVER_ERROR);
    }
}
