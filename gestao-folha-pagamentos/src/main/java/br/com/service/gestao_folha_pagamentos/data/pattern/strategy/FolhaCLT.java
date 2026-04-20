package br.com.service.gestao_folha_pagamentos.data.pattern.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Component
public class FolhaCLT implements CalculoFolha {
    @Override
    public BigDecimal cacular(BigDecimal salarioBruto) {
        // 1. FGTS (Multiplicação simples geralmente não pede rounding, mas é bom garantir)
        BigDecimal fgts = salarioBruto.multiply(new BigDecimal("0.08")).setScale(2, RoundingMode.HALF_UP);

// 2. Cálculo das férias + 1/3
        BigDecimal mesesDoAno = new BigDecimal("12");
        BigDecimal terco = new BigDecimal("3");

// Calculamos o valor mensal das férias
        BigDecimal feriasMensal = salarioBruto.divide(mesesDoAno, 4, RoundingMode.HALF_UP);
// Calculamos o terço sobre esse valor mensal
        BigDecimal umTercoDasFerias = feriasMensal.divide(terco, 4, RoundingMode.HALF_UP);

// Somamos e agora sim aplicamos a escala de 2 com o modo de arredondamento
        BigDecimal feriasEumTerco = feriasMensal.add(umTercoDasFerias).setScale(2, RoundingMode.HALF_UP);

// 3. 13° (Corrigido: ponto em vez de vírgula)
// Usar 0.0833 é uma aproximação de 1/12.
        BigDecimal decimoTerceiro = salarioBruto.multiply(new BigDecimal("0.0833")).setScale(2, RoundingMode.HALF_UP);

// 4. Retorno final (Soma tudo e garante a escala final)
        return salarioBruto.add(feriasEumTerco)
                .add(fgts)
                .add(decimoTerceiro)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
