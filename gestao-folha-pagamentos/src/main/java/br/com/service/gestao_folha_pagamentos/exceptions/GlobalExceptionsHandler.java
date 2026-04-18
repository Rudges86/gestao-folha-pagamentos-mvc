package br.com.service.gestao_folha_pagamentos.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroMensagem> runtimeException(Exception ex, HttpRequest request) {
        logger.error("ERRO GLOBAL: ".concat(ex.getLocalizedMessage()));
        return ResponseEntity.badRequest().body(new ErroMensagem("MSG26",ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,request.getURI().toString()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroMensagem> dataIntegrityViolation(DataIntegrityViolationException ex, HttpRequest request){
        var mensagem = messageSource.getMessage("MSG22",null,null);
        logger.error("Erro ao realizar operação no banco: ".concat(ex.getMessage()));
        return ResponseEntity.badRequest().body(new ErroMensagem("MSG22",mensagem, HttpStatus.BAD_REQUEST, request.getURI().toString()));
    }

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BussinessException ex, HttpRequest request) {
        var mensagens = ex.getCode().stream().map(e -> {
           String mensagem  = messageSource.getMessage(e,null,null);
            return new ErroMensagem(e,mensagem, HttpStatus.BAD_REQUEST, request.getURI().toString());
        }).toList();
        if(mensagens.size() == 1) {
            return ResponseEntity.badRequest().body(mensagens.get(0));
        }
        return ResponseEntity.badRequest().body(mensagens);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotvalidException(MethodArgumentNotValidException ex, HttpRequest request) {
        var mensagens = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
                String mensagem = messageSource.getMessage(fieldError, null);
                    return new ErroMensagem(fieldError.getCode(),mensagem, HttpStatus.BAD_REQUEST, request.getURI().toString());
                }).collect(Collectors.toList());

        if(mensagens.size() == 1) {
            return ResponseEntity.badRequest().body(mensagens.get(0));
        }
        return ResponseEntity.badRequest().body(mensagens);
    }

}
