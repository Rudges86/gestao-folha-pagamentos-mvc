package br.com.service.gestao_folha_pagamentos.data.dto.funcionario;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public record FiltroPesquisaFuncionarioDTO(
        UUID id,
        String nome,
        Integer idade,
        String bairro,
        String estado,
        String pais,
        Boolean ativo
) {
}
