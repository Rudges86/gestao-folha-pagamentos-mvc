package br.com.service.gestao_folha_pagamentos.controller;

import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolha;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolhaPorFuncionarioProjection;
import br.com.service.gestao_folha_pagamentos.service.FolhaPagamentosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/folhaPagamento")
public class FolhaPagamentosController {

    private final FolhaPagamentosService service;

    public FolhaPagamentosController(FolhaPagamentosService service) {
        this.service = service;
    }

    @GetMapping("/calculoFolhaTotal")
    ResponseEntity<List<TotalFolha>> totalFolha(@RequestParam(required = false) String tipoContratacao) {

        var totalFolha =  tipoContratacao == null || tipoContratacao.isBlank()  ? service.calculaFolhaTotal(null)
                : service.calculaFolhaTotal(TipoContratacao.toEnum(tipoContratacao));
        return ResponseEntity.ok(totalFolha);
    }

    @GetMapping("/calculoFolhaIndividual")
    ResponseEntity<Page<TotalFolhaPorFuncionarioProjection>> folhaIndividual(@RequestParam String tipoContratacao,
                                                                             @PageableDefault(size = 10, sort = "nome") Pageable page) {
        var totalFolha = service.folhaPaginada(TipoContratacao.toEnum(tipoContratacao), page);
        return ResponseEntity.ok(totalFolha);
    }

}
