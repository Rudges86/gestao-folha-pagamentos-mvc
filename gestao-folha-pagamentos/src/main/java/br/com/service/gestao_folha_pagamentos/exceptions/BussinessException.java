package br.com.service.gestao_folha_pagamentos.exceptions;

import java.util.List;

public class BussinessException extends RuntimeException {
    private final List<String> code;
    public BussinessException(String code) {
        super(code);
        this.code = List.of(code);
    }

    public BussinessException(List<String> code) {
        super(String.join(", ", code));
        this.code = code;
    }

    public List<String> getCode() {
        return code;
    }
}
