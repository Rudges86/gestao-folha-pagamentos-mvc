package br.com.service.gestao_folha_pagamentos.data.pattern.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Component
public class FolhaPJ implements CalculoFolha {
    @Override
    public BigDecimal cacular(BigDecimal salarioBruto) {
        return salarioBruto.setScale(2, RoundingMode.HALF_UP);
    }
}
