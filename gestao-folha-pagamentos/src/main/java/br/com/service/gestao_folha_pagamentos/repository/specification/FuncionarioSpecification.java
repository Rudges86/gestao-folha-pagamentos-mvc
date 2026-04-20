package br.com.service.gestao_folha_pagamentos.repository.specification;

import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FiltroPesquisaFuncionarioDTO;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import br.com.service.gestao_folha_pagamentos.repository.projection.TotalFolha;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioSpecification {

    public static Specification<Funcionario> combinacaoFiltros(FiltroPesquisaFuncionarioDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filtro.id() != null){
                predicates.add(cb.equal(root.get("id"), filtro.id()));
            }
            if(filtro.matricula() != null) {
                predicates.add(cb.equal(root.get("matricula"), filtro.matricula()));
            }

            if(filtro.idade() != null) {
                predicates.add(cb.equal(root.get("idade"), filtro.idade()));
            }

            if(filtro.nome() != null) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.nome().toLowerCase() + "%"));
            }

            if(filtro.ativo() != null){
                predicates.add(cb.equal(root.get("isAtivo"), filtro.ativo()));

            }

            if(filtro.bairro() != null){
                predicates.add(cb.equal(root.get("endereco").get("bairro"), filtro.bairro()));
            }

            if(filtro.estado() != null){
                predicates.add(cb.equal(root.get("endereco").get("estado"), filtro.estado()));
            }

            if(filtro.pais() != null){
                predicates.add(cb.equal(root.get("endereco").get("pais"), filtro.pais()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


    public static Specification<List<TotalFolha>> totalFolha (TipoContratacao tipoContratacao) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("isAtivo"), true));

            if(tipoContratacao.getDescricao().equals("CLT")) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")),tipoContratacao.getDescricao().toLowerCase()));
            }

            if(tipoContratacao.getDescricao().equals("PJT")) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")),tipoContratacao.getDescricao().toLowerCase()));
            }

            if(tipoContratacao.getDescricao().equals("ESTÁGIO")) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")),tipoContratacao.getDescricao().toLowerCase()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
