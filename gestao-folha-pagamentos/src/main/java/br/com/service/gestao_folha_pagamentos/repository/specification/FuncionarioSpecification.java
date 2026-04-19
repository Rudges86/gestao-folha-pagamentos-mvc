package br.com.service.gestao_folha_pagamentos.repository.specification;

import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FiltroPesquisaFuncionarioDTO;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
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

            if(filtro.nome() != null) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.nome().toLowerCase() + "%"));
            }

            if(filtro.ativo() != null){
                predicates.add(cb.equal(root.get("ativo"), filtro.ativo()));

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
}
