package br.com.service.gestao_folha_pagamentos.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroMensagem> runtimeException(Exception ex, HttpServletRequest request) {
        logger.error("ERRO GLOBAL: ".concat(ex.getLocalizedMessage()));
        return ResponseEntity.badRequest().body(new ErroMensagem("MSG26",ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,request.getRequestURI()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroMensagem> dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request){
        var mensagem = messageSource.getMessage("MSG22",null,null);
        logger.error("Erro ao realizar operação no banco: ".concat(ex.getMessage()));
        return ResponseEntity.badRequest().body(new ErroMensagem("MSG22",mensagem, HttpStatus.BAD_REQUEST, request.getRequestURI()));
    }

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BussinessException ex, HttpServletRequest request) {
        var mensagens = ex.getCode().stream().map(e -> {
           String mensagem  = messageSource.getMessage(e,null,null);
            return new ErroMensagem(e,mensagem, HttpStatus.BAD_REQUEST, request.getRequestURI());
        }).toList();

        return  montaRetorno(mensagens);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroMensagem>> methodArgumentNotvalidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var mensagens = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
                if(fieldError.getDefaultMessage().contains("java.lang")) {
                    return new ErroMensagem("MSG26", fieldError.getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
                }
            String codigoErro = fieldError.getDefaultMessage();
            String mensagem = messageSource.getMessage(codigoErro,null, null);
                    return new ErroMensagem(codigoErro,mensagem, HttpStatus.BAD_REQUEST, request.getRequestURI());
                }).collect(Collectors.toList());

        return montaRetorno(mensagens);
    }

    private ResponseEntity<List<ErroMensagem>> montaRetorno(List<ErroMensagem> mensagens) {
        if(mensagens.size() == 1) {
            return mensagens.get(0).getMensagem().contains("java.lang") ?
                    ResponseEntity.internalServerError().body( mensagens) : ResponseEntity.badRequest().body(mensagens);
        }

        return ResponseEntity.badRequest().body(mensagens);
    }
}
