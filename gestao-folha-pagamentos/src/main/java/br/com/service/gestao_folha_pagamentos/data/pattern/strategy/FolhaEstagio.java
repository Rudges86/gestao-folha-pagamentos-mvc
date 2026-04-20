package br.com.service.gestao_folha_pagamentos.data.pattern.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Component
public class FolhaEstagio  implements CalculoFolha {
    @Override
    public BigDecimal cacular(BigDecimal salarioBruto) {
        BigDecimal ferias = salarioBruto.divide(
                salarioBruto.divide(new BigDecimal("12")),4, RoundingMode.HALF_UP)
                .setScale(2);
        return salarioBruto.add(ferias).setScale(2,RoundingMode.HALF_UP);
    }
}
