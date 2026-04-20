package br.com.service.gestao_folha_pagamentos.data.pattern.strategy;

import java.math.BigDecimal;

public interface CalculoFolha {
    BigDecimal cacular(BigDecimal salarioBruto);
}
