package br.com.service.gestao_folha_pagamentos.repository;

import br.com.service.gestao_folha_pagamentos.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TelefoneRepository extends JpaRepository<Telefone, UUID> {
}
