package br.com.service.gestao_folha_pagamentos.data.dto.funcionario;

import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FuncionarioResponseDTO(
        UUID id,
        String nome,
        Integer idade,
        LocalDate dataNascimento,
        Character sexo,
        String escolaridade,
        String funcao,
        BigDecimal salario,
        Boolean ativo,
        LocalDate dataDesligamento,
        TipoContratacao tipoContratacao,
        List<TelefoneDTO> telefone,
        EnderecoDTO endereco
) {
}
