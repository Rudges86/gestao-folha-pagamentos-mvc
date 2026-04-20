package br.com.service.gestao_folha_pagamentos.service;

import br.com.service.gestao_folha_pagamentos.data.dto.ResponseDTOMensage;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FiltroPesquisaFuncionarioDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioResponseDTO;
import br.com.service.gestao_folha_pagamentos.data.mapper.FuncionarioMapper;
import br.com.service.gestao_folha_pagamentos.exceptions.BussinessException;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.repository.FuncionarioRepository;
import br.com.service.gestao_folha_pagamentos.repository.specification.FuncionarioSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;


@Service
public class FuncionarioService {
    private FuncionarioRepository repository;
    private MessageSource source;
    private final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

    public FuncionarioService(FuncionarioRepository repository, MessageSource source) {
        this.repository = repository;
        this.source = source;
    }

    @Transactional
    public ResponseDTOMensage cadastrarFuncionario(FuncionarioRequestDTO dto) {
        var entity = FuncionarioMapper.toEntity(dto);
        logger.info("Salvando o usuário.");
        entity.getEndereco().setFuncionario(entity);
        entity.getTelefones().forEach(e -> e.setFuncionario(entity));
        entity.setIsAtivo(true);
        entity.setMatricula(gerarMatricula());
        repository.save(entity);
        logger.info("Salvo com sucesso.");
        return new ResponseDTOMensage("MSG24", source.getMessage("MSG24", null, null));
    }


    @Transactional
    public ResponseDTOMensage desiligarFuncionario(UUID id) {
        var funcionario = findByID(id);
        funcionario.setIsAtivo(false);
        funcionario.setDataDesligamento(LocalDate.now());
        return new ResponseDTOMensage("MSG25", source.getMessage("MSG25", null, null));
    }

    @Transactional
    public ResponseDTOMensage religarFuncionario(UUID id) {
        var funcionario = findByID(id);
        funcionario.setIsAtivo(true);
        return new ResponseDTOMensage("MSG27", source.getMessage("MSG27", null, null));
    }

    @Transactional
    public FuncionarioResponseDTO atualizarCadastroFuncionario(UUID id,  FuncionarioRequestDTO dto) {
        var entity = findByID(id);
        FuncionarioMapper.update(dto, entity);
       return FuncionarioMapper.toDTO(repository.save(entity));
    }

    private Funcionario findByID(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new BussinessException("MSG23"));
    }


    public Page<FuncionarioResponseDTO> buscar(FiltroPesquisaFuncionarioDTO filtro, Pageable page) {
        Specification spec = FuncionarioSpecification.combinacaoFiltros(filtro);
        Page<Funcionario> allEntity =  repository.findAll(spec, page);
        return allEntity.map(FuncionarioMapper::toDTO);
    }


    private Long gerarMatricula() {
       var entity =  repository.findMaxMatricula();
       return entity.isPresent() ? entity.get() + 1 : 00000001;
    }
}
