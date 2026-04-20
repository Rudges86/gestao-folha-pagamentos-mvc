package br.com.service.gestao_folha_pagamentos.model.enumerated;

import br.com.service.gestao_folha_pagamentos.exceptions.BussinessException;

import java.util.Arrays;

public enum TipoContratacao {
    CLT ("CLT", 1),
    PJ("PJ",2),
    ESTAGIO("ESTAGIO", 3);

    private String descricao;
    private Integer codigo;

    TipoContratacao(String descricao, Integer codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public static TipoContratacao toEnum(Integer cod) {
        return Arrays.stream(TipoContratacao.values())
                .filter( codi -> cod.equals(codi.getCodigo()))
                .findFirst()
                .orElseThrow(() ->  new BussinessException("MSG30"));
    }

    public static TipoContratacao toEnum(String descricao) {
        return Arrays.stream(TipoContratacao.values())
                .filter( codi -> descricao.toUpperCase().equals(codi.getDescricao()))
                .findFirst()
                .orElseThrow(() -> new BussinessException("MSG29"));
    }

    public static String descricao(String descricao) {
        return Arrays.stream(TipoContratacao.values())
                .filter(y -> y.getDescricao().equals(descricao.toUpperCase()))
                .map(TipoContratacao::getDescricao)
                .findFirst()
                .orElseThrow(() -> new BussinessException("MSG29"));
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
