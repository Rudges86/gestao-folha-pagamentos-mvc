package br.com.service.gestao_folha_pagamentos.repository;

import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolha;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolhaPorFuncionarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>, JpaSpecificationExecutor<Funcionario> {
    @Query(nativeQuery = true, value = """
            SELECT MAX(f.matricula) FROM funcionario f
            """)
    Optional<Long> findMaxMatricula();

    @Query(nativeQuery = true, value = """
            SELECT TIPO, SUM(F.SALARIO) AS FOLHA  FROM FUNCIONARIO F
            WHERE F.IS_ATIVO = TRUE
            GROUP BY F.TIPO
            ;
            """)

    List<TotalFolha> findTotalFolha();

    @Query(nativeQuery = true, value = """
            SELECT NOME,TIPO, SALARIO   FROM FUNCIONARIO F
            WHERE F.IS_ATIVO = TRUE
            AND LOWER(F.TIPO) = LOWER(:tipo)
            """,
    countQuery = """
            SELECT COUNT(F.TIPO) FROM FUNCIONARIO F
            WHERE F.IS_ATIVO = TRUE
            AND LOWER(F.TIPO) = LOWER(:tipo)          
            """
    )
    Page<TotalFolhaPorFuncionarioProjection> findForTipo(@Param(value = "tipo") String tipo, Pageable pageable);

}
