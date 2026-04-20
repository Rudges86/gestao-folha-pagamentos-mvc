package br.com.service.gestao_folha_pagamentos.repository.projection;

import java.math.BigDecimal;
import java.util.Objects;

public class TotalFolhaPorFuncionarioProjection extends TotalFolha{
    private String nome;

    public TotalFolhaPorFuncionarioProjection(String nome, String tipo, BigDecimal folha) {
        super(tipo, folha);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalFolhaPorFuncionarioProjection that = (TotalFolhaPorFuncionarioProjection) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
