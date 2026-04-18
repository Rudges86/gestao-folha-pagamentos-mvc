package br.com.service.gestao_folha_pagamentos.data.dto;

import br.com.service.gestao_folha_pagamentos.data.models.PadraoMensagem;

public class ResponseDTOMensage extends PadraoMensagem {

    public ResponseDTOMensage() {
    }

    public ResponseDTOMensage(String code, String message) {
        super(code, message);
    }
}
