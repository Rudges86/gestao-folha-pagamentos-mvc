package br.com.service.gestao_folha_pagamentos.data.models;

public class PadraoMensagem {
    private String mensagem;
    private String codigoMsg;

    public PadraoMensagem() {
    }

    public PadraoMensagem(String code, String mensagem) {
        this.mensagem = mensagem;
        this.codigoMsg = code;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCodigoMsg() {
        return codigoMsg;
    }

    public void setCodigoMsg(String codigoMsg) {
        this.codigoMsg = codigoMsg;
    }
}
