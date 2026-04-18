package br.com.service.gestao_folha_pagamentos.exceptions;

import br.com.service.gestao_folha_pagamentos.data.models.PadraoMensagem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatusCode;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroMensagem extends PadraoMensagem {
    private static final long serialVersionUID = 1L;
    private HttpStatusCode code;
    private String path;

    public ErroMensagem() {
        super();
    }

    public ErroMensagem(String msgCode, String mensagem, HttpStatusCode code, String path) {
        super(msgCode, mensagem);
        this.code = code;
        this.path = path;
    }

    public HttpStatusCode getCode() {
        return code;
    }

    public void setCode(HttpStatusCode code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
