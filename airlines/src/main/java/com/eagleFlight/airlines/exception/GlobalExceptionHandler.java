package com.eagleFlight.airlines.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErrosValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> listaErros = new HashMap<>();

        // Verificação dos dados que falharam para customização de mensagem de erro
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeCampo = ((FieldError) error).getField();
            String mensagemErro = error.getDefaultMessage();
            listaErros.put(nomeCampo, mensagemErro);
        });

        // Retorna o status 400 Bad Request limpo, contendo apenas o mapa com os erros formatados.
        return new ResponseEntity<>(listaErros, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> tratarElementoNaoEncontrado(NoSuchElementException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", "A informação solicitada não foi encontrada no sistema (ID inválido ou inexistente).");

        // Retorna o erro 404 Not Found limpo.
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
}