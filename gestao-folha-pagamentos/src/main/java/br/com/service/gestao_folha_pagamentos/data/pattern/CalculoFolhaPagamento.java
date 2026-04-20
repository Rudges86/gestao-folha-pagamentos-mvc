package br.com.service.gestao_folha_pagamentos.data.pattern;

import br.com.service.gestao_folha_pagamentos.data.pattern.strategy.CalculoFolha;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculoFolhaPagamento {

    private CalculoFolha calculoFolha;

    public CalculoFolhaPagamento() {
    }

    public void setCalculoFolha(CalculoFolha calculoFolha) {
        this.calculoFolha = calculoFolha;
    }

    public  BigDecimal calcularFolha(BigDecimal salarioBruto) {
        return calculoFolha.cacular(salarioBruto);
    };
}
