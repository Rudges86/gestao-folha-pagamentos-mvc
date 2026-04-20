package br.com.service.gestao_folha_pagamentos.controller;

import br.com.service.gestao_folha_pagamentos.controller.docs.FuncionarioDoc;
import br.com.service.gestao_folha_pagamentos.data.dto.ResponseDTOMensage;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FiltroPesquisaFuncionarioDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioResponseDTO;
import br.com.service.gestao_folha_pagamentos.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/funcionario")
@Tag(name = "Funcionários", description = "EndPoint de gerenciamento de funcionários.")
public class FuncionarioController implements FuncionarioDoc {

    private FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    //Paginação
    @GetMapping("/listar-funcionarios")
    public ResponseEntity<Page<FuncionarioResponseDTO>> listarTodosFuncionarios(
            @ModelAttribute FiltroPesquisaFuncionarioDTO filtro,
            @PageableDefault(size = 10, sort = "nome")Pageable page) {
        return ResponseEntity.ok(service.buscar(filtro, page));
    }


    @PostMapping("/cadastrar-funcionario")
    public ResponseEntity<ResponseDTOMensage> cadastrarFuncionario(@RequestBody @Valid FuncionarioRequestDTO dto) {
        ResponseDTOMensage response =  service.cadastrarFuncionario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/atualizarCadastro/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarCadastro(@PathVariable(name = "id") UUID id, @RequestBody FuncionarioRequestDTO dto) {
        var response = service.atualizarCadastroFuncionario(id,dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/inativarFuncionario/{id}")
    public ResponseEntity<ResponseDTOMensage> desligarFuncionario(@PathVariable(name = "id") UUID id) {
        ResponseDTOMensage responseDTO = service.desiligarFuncionario(id);
        return ResponseEntity.ok().body(responseDTO);
    }


    @PutMapping("/religarFuncionario/{id}")
    public ResponseEntity<ResponseDTOMensage> religarFuncionario(@PathVariable(name = "id") UUID id) {
        ResponseDTOMensage responseDTO = service.religarFuncionario(id);
        return ResponseEntity.ok().body(responseDTO);
    }
}
