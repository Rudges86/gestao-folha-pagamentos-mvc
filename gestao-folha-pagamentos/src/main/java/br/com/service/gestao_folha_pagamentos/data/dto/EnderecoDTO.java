package br.com.service.gestao_folha_pagamentos.data.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull(message = "{MSG16}")
        @NotBlank(message = "{MSG16}")
        String rua,
        @NotNull(message = "{MSG17}")
        @NotBlank(message = "{MSG17}")
        String bairro,
        @NotNull(message = "{MSG18}")
        @NotBlank(message = "{MSG18}")
        String municipio,
        @NotNull(message = "{MSG19}")
        @NotBlank(message = "{MSG19}")
        String estado,
        @NotNull(message = "{MSG20}")
        @NotBlank(message = "{MSG20}")
        String pais,
        @NotNull(message = "{MSG21}")
        @NotBlank(message = "{MSG21}")
        String cep
) {
}
