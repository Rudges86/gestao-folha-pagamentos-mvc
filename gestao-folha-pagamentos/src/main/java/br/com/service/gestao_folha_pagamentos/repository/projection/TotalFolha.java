package br.com.service.gestao_folha_pagamentos.repository.projection;

import java.io.Serializable;
import java.math.BigDecimal;
//Pode usar como recorde e como interface, interface melhor para leitura e melhor integração com o JPA
//public record TotalFolha (String tipo, BigDecimal folha) {}
//public interface TotalFolha {
//    String getTipo();
//    BigDecimal getFolha();
//}


public class TotalFolha implements Serializable {
    private static final Long serialVersionUID = 1L;
    private String tipo;
    private BigDecimal folha;

    public TotalFolha(String tipo, BigDecimal folha) {
        this.tipo = tipo;
        this.folha = folha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getFolha() {
        return folha;
    }

    public void setFolha(BigDecimal folha) {
        this.folha = folha;
    }
}
