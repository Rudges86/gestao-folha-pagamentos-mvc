package br.com.service.gestao_folha_pagamentos.data.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FuncionarioResponseDTO(
        String nome,
        Integer idade,
        LocalDate dataNascimento,
        Character sexo,
        String escolaridade,
        String funcao,
        BigDecimal salario,
        Boolean ativo,
        LocalDate dataDesligamento,
        List<TelefoneDTO> telefone,
        EnderecoDTO enderecoDTO
) {
}
