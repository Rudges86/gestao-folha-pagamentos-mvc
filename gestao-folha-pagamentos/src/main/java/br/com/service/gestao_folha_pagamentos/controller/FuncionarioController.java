package br.com.service.gestao_folha_pagamentos.controller;

import br.com.service.gestao_folha_pagamentos.controller.docs.FuncionarioDoc;
import br.com.service.gestao_folha_pagamentos.data.dto.CadastroFuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.FuncionarioResponseDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.ResponseDTOMensage;
import br.com.service.gestao_folha_pagamentos.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/funcionario")
public class FuncionarioController implements FuncionarioDoc {

    private FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    //Paginação
    @GetMapping("/listar-funcionarios")
    public ResponseEntity<PagedModel<FuncionarioResponseDTO>> listarTodosFuncionarios(
            @RequestParam(name = "id", required = false) UUID id,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "idade", required = false) Integer idade,
            @RequestParam(name = "bairro", required = false) String bairro,
            @RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "pais", required = false) String pais,
            @RequestParam(name = "ativo", required = false) Boolean ativo
    ) {
        return ResponseEntity.ok().build();
    }


    @PostMapping("/cadastrar-funcionario")
    public ResponseEntity<ResponseDTOMensage> cadastrarFuncionario(@RequestBody @Valid CadastroFuncionarioRequestDTO dto) {
        ResponseDTOMensage response =  service.cadastrarFuncionario(dto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/atualizarCadastro/{id}")
    public ResponseEntity<?> atualizarCadastro(@PathVariable(name = "id") UUID id) {

        return ResponseEntity.ok().build();
    }


    @PutMapping("/inativarFuncionario/{id}")
    public ResponseEntity<ResponseDTOMensage> desligarFuncionario(@PathVariable(name = "id") UUID id) {
        ResponseDTOMensage responseDTO = service.desiligarFuncionario(id);
        return ResponseEntity.ok().body(responseDTO);
    }

}
