package br.com.service.gestao_folha_pagamentos.service;

import br.com.service.gestao_folha_pagamentos.data.pattern.CalculoFolhaPagamento;
import br.com.service.gestao_folha_pagamentos.data.pattern.strategy.FolhaCLT;
import br.com.service.gestao_folha_pagamentos.data.pattern.strategy.FolhaEstagio;
import br.com.service.gestao_folha_pagamentos.data.pattern.strategy.FolhaPJ;
import br.com.service.gestao_folha_pagamentos.data.utils.GlobalStringUtils;
import br.com.service.gestao_folha_pagamentos.exceptions.BussinessException;
import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import br.com.service.gestao_folha_pagamentos.repository.FuncionarioRepository;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolha;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolhaPorFuncionarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolhaPagamentosService {

    private final FuncionarioRepository repository;
    private final CalculoFolhaPagamento calculoFolhaPagamento;


    public FolhaPagamentosService(FuncionarioRepository repository, CalculoFolhaPagamento calculoFolhaPagamento) {
        this.repository = repository;
        this.calculoFolhaPagamento = calculoFolhaPagamento;
    }

    public List<TotalFolha> calculaFolhaTotal(TipoContratacao tipoContratacao) {

        if(tipoContratacao == null) {
            return  repository.findTotalFolha().stream().map(e -> processarFolhaPorFunconario(e.getTipo(), e.getFolha())).collect(Collectors.toList());
        }

        Pageable pageable = PageRequest.of(0,10);
        Page <TotalFolhaPorFuncionarioProjection> folha = repository.findForTipo(tipoContratacao.getDescricao(), pageable);
        if(folha.getContent().isEmpty()) {
            throw new BussinessException("MSG28");
        }
        BigDecimal total = BigDecimal.ZERO;
        int page = 0;

        do{
            total = total.add(folha.stream().map(e -> processarFolha(e.getTipo(), e.getFolha()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add)).setScale(2, RoundingMode.HALF_UP);
            folha = repository.findForTipo(tipoContratacao.getDescricao(), pageable);
            page++;
        } while(folha.hasNext());
        return List.of(new TotalFolha(tipoContratacao.getDescricao(),total));

       // return folha.stream().map(e -> processarFolha(e.getTipo(), e.getFolha())).collect(Collectors.toList());
    }



    public Page<TotalFolhaPorFuncionarioProjection> folhaPaginada(TipoContratacao tipo,Pageable pageable) {
        Page <TotalFolhaPorFuncionarioProjection> folha = repository.findForTipo(tipo.getDescricao(), pageable);
        if(folha.getContent().isEmpty()) {
            throw new BussinessException("MSG28");
        }
        return folha;
    }



    private BigDecimal processarFolha (String tipo, BigDecimal salario) {
        switch (tipo) {
            case GlobalStringUtils.CLT -> {
                calculoFolhaPagamento.setCalculoFolha(new FolhaCLT());
            }
            case GlobalStringUtils.PJ -> {
                calculoFolhaPagamento.setCalculoFolha(new FolhaPJ());
            }
            case GlobalStringUtils.ESTAGIO ->{
                calculoFolhaPagamento.setCalculoFolha(new FolhaEstagio());
            }
        }
        return calculoFolhaPagamento.calcularFolha(salario);
    }

    private TotalFolha processarFolhaPorFunconario (String tipo, BigDecimal salario) {
        var resultado = processarFolha(tipo, salario);
        return new TotalFolha(tipo, resultado);
    }

}


