package br.com.service.gestao_folha_pagamentos.data.dto.funcionario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(

        @NotBlank(message = "MSG16")
        String rua,

        @NotBlank(message = "MSG17")
        String bairro,

        @NotBlank(message = "MSG18")
        String municipio,

        @NotBlank(message = "MSG19")
        String estado,

        @NotBlank(message = "MSG20")
        String pais,

        @NotBlank(message = "MSG21")
        String cep
) {
}
