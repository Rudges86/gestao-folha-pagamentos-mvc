package br.com.service.gestao_folha_pagamentos.data.mapper;

import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioResponseDTO;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.model.entity.Telefone;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    FuncionarioMapper INSTANCE = Mappers.getMapper(FuncionarioMapper.class);

    static Funcionario toEntity(FuncionarioRequestDTO dto) {
        return INSTANCE.mapToEntity(dto);
    }

    @Mapping(target = "telefones",source = "telefone")
    @Mapping(target = "endereco",source = "endereco")
    @Mapping(target = "tipo", source = "tipoContratacao")
    Funcionario mapToEntity(FuncionarioRequestDTO dto);


    static FuncionarioResponseDTO toDTO(Funcionario funcionario) {
        return FuncionarioMapper.INSTANCE.mapToDTO(funcionario);
    }

    @Mapping(target = "tipoContratacao", source = "tipo")
    @Mapping(target="telefone", source = "telefones")
    FuncionarioResponseDTO mapToDTO(Funcionario funcionario);

    static void update(FuncionarioRequestDTO dto, Funcionario entity) {
        FuncionarioMapper.INSTANCE.updateFuncionario(dto, entity);
    }

    @Mapping(target = "telefones",source = "telefone")
    @Mapping(target = "endereco",source = "endereco")
    @Mapping(target = "tipo", source = "tipoContratacao")
    void updateFuncionario(FuncionarioRequestDTO dto, @MappingTarget Funcionario entity);

    @AfterMapping
    default void mapperRelacionamentos(@MappingTarget Funcionario funcionario) {
        if(funcionario.getEndereco() != null) {
            funcionario.getEndereco().setFuncionario(funcionario);
        }

        if(funcionario.getTelefones() != null) {
            funcionario.getTelefones().forEach(e -> e.setFuncionario(funcionario));
        }
    }
}
