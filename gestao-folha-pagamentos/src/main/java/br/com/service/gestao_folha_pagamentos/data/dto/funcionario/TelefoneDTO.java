package br.com.service.gestao_folha_pagamentos.data.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneDTO(

        @NotBlank(message = "MSG13")
        String numero,

        @NotBlank(message = "MSG14")
        String tipo
) {}
