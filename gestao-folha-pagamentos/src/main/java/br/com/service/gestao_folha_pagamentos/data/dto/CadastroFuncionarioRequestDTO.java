package br.com.service.gestao_folha_pagamentos.data.dto;

import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CadastroFuncionarioRequestDTO(
        @NotNull(message = "{MSG01}")
        @NotBlank(message = "{MSG01}")
        String nome,
        @NotNull(message = "{MSG02}")
        Integer idade,
        @NotNull(message = "{MSG04}")
        LocalDate dataNascimento,
        @NotNull(message = "{MSG08}")
        Character sexo,
        @NotNull(message = "{MSG09}")
        @NotBlank(message = "{MSG09}")
        String escolaridade,
        @NotNull(message = "{MSG10}")
        @NotBlank(message = "{MSG10}")
        String funcao,
        @NotNull(message = "{MSG05}")
        BigDecimal salario,
        @NotNull
        TipoContratacao tipoContratacao,
        Boolean ativo,
        LocalDate dataDesligamento,
        @Valid
        @NotNull(message = "{MSG11}")
        List<TelefoneDTO> telefone,
        @Valid
        @NotNull(message = "{MSG12}")
        EnderecoDTO endereco
) {
}
