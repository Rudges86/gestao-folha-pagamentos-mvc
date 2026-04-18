package br.com.service.gestao_folha_pagamentos.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneDTO(
        @NotNull(message = "{MSG13}")
        @NotBlank(message = "{MSG13}")
        String telefone,
        @NotNull(message = "{MSG14}")
        @NotBlank(message = "{MSG14}")
        String tipo
) {}
