package br.com.service.gestao_folha_pagamentos.service;

import br.com.service.gestao_folha_pagamentos.data.dto.CadastroFuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.ResponseDTOMensage;
import br.com.service.gestao_folha_pagamentos.data.mapper.FuncionarioMapper;
import br.com.service.gestao_folha_pagamentos.exceptions.BussinessException;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.repository.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
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
    public ResponseDTOMensage cadastrarFuncionario(CadastroFuncionarioRequestDTO dto) {
        var entity = FuncionarioMapper.INSTANCE.toEntity(dto);
        logger.info("Salvando o usuário.");
        entity.getEndereco().setFuncionario(entity);
        entity.getTelefones().forEach(e -> e.setFuncionario(entity));
        repository.save(entity);
        logger.info("Salvo com sucesso.");
        return new ResponseDTOMensage("MSG24", source.getMessage("MSG24", null, null));
    }


    @Transactional
    public ResponseDTOMensage desiligarFuncionario(UUID id) {
        var funcionario = findByID(id);
        funcionario.setIsAtivo(false);
        funcionario.setDataDesligamento(LocalDate.now());
        return new ResponseDTOMensage("MSG24", source.getMessage("MSG25", null, null));
    }

    private Funcionario findByID(UUID id) {
        return repository.findById(id)
                .orElseThrow(()-> new BussinessException("MSG23"));
    }


}
