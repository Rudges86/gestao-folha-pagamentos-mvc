package br.com.service.gestao_folha_pagamentos.data.mapper;

import br.com.service.gestao_folha_pagamentos.data.dto.CadastroFuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FuncionarioMapper {

    FuncionarioMapper INSTANCE = Mappers.getMapper(FuncionarioMapper.class);

    @Mapping(target = "telefones",source = "telefone")
    @Mapping(target = "endereco",source = "endereco")
    @Mapping(target = "tipo", source = "tipoContratacao")
    Funcionario toEntity(CadastroFuncionarioRequestDTO dto);
}
