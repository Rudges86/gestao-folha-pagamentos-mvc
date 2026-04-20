package br.com.service.gestao_folha_pagamentos.controller.docs;

import br.com.service.gestao_folha_pagamentos.data.dto.ResponseDTOMensage;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FiltroPesquisaFuncionarioDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface FuncionarioDoc {

    @Operation(summary = "Listar todos os funcionários com base no critério de pesquisa",
            description = "EndPoint para listar todos os funcionários com base no critério de pesquisa escolhido.",
            tags ={"Funcionários"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = FuncionarioResponseDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    @GetMapping("/listar-funcionarios")
    public ResponseEntity<Page<FuncionarioResponseDTO>> listarTodosFuncionarios(
            FiltroPesquisaFuncionarioDTO filtro,
            @PageableDefault(size = 10, sort = "nome") Pageable page
    );

    @Operation(summary = "Cadastra um novo funcionário",
            description = "Cadastra um novo funcionário.",
            tags = {"Funcionários"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = FuncionarioRequestDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping("/cadastrar-funcionario")
    public ResponseEntity<ResponseDTOMensage> cadastrarFuncionario(@RequestBody @Valid FuncionarioRequestDTO dto);

    @Operation(summary = "Edita alguma informação do funcionário.",
            description = "Faz o update das informações do usuário",
            tags = {"Funcionários"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = FuncionarioRequestDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )

    @PutMapping("/atualizarCadastro/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarCadastro(@PathVariable(name = "id") UUID id, @RequestBody FuncionarioRequestDTO dto );


    @Operation(summary = "Inativa um funcionário na base",
            description = """
                    EndPoint criado para substituir o dele, ele vai inativar o funcionário após o desligamento,
                    para que o cálculo da folha seja feito com os funcionários ativos, mantendo assim um histórico
                    na base caso um funcionário volte para a empresa.                    
                    """,
            tags = {"Funcionários"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = FuncionarioRequestDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping("/inativarFuncionario/{id}")
    public ResponseEntity<ResponseDTOMensage> desligarFuncionario(@PathVariable(name = "id") UUID id);


    @Operation(summary = "Reativar um funcionário na base",
            description = """
                    EndPoint criado para reativar um funcionário no caso de voltar para a empresa,
                    para que o cálculo da folha seja feito com os funcionários ativos, mantendo assim um histórico
                    na base caso um funcionário volte para a empresa.                    
                    """,
            tags = {"Funcionários"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = FuncionarioRequestDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping("/religarFuncionario/{id}")
    ResponseEntity<ResponseDTOMensage> religarFuncionario(@PathVariable(name = "id") UUID id);
}
