package br.com.service.gestao_folha_pagamentos.repository;

import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>, JpaSpecificationExecutor<Funcionario> {
}
