package br.com.service.gestao_folha_pagamentos.config;

import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.EnderecoDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.FuncionarioRequestDTO;
import br.com.service.gestao_folha_pagamentos.data.dto.funcionario.TelefoneDTO;
import br.com.service.gestao_folha_pagamentos.model.entity.Endereco;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.model.entity.Telefone;
import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import br.com.service.gestao_folha_pagamentos.service.FuncionarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@Profile("teste")
public class DataLoader implements CommandLineRunner {

    private final FuncionarioService service;

    public DataLoader(FuncionarioService service) {
        this.service = service;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("### Iniciando carga de dados no H2... ###");

        List<FuncionarioRequestDTO> funcionarios = List.of(
                criarFuncionario("Ana Silva", 28, "CLT", "8500.00", "Aracaju", "SE","Programador", 'F',"Superior"),
                criarFuncionario("Bruno Costa", 35, "PJ", "12000.00", "Nossa Senhora do Socorro", "AM","Administrativo",'M',"Superior"),
                criarFuncionario("Carla Souza", 22, "ESTAGIO", "1500.00", "São Cristóvão", "SP", "Administrativo",'M', "Médio"),
                criarFuncionario("Diego Oliveira", 40, "CLT", "9800.00", "Lagarto", "AL","Marketing",'M',"Médio"),
                criarFuncionario("Eliana Santos", 31, "CLT", "7200.00", "Itabaiana", "CE","Recepcionista",'F',"Superior")
        );

        funcionarios.stream().forEach(f -> {
            service.cadastrarFuncionario(f);
        });

        System.out.println("### " + funcionarios.size() + " Funcionários cadastrados com sucesso! ###");
    }

    private FuncionarioRequestDTO criarFuncionario(String nome, Integer idade, String tipo, String salario, String cidade, String uf, String funcao, char sexo, String escolaridade) {
        FuncionarioRequestDTO f = new FuncionarioRequestDTO(nome, idade, LocalDate.now(), sexo,
                escolaridade,funcao, BigDecimal.valueOf(Double.parseDouble(salario)),TipoContratacao.valueOf(tipo),null,null,
                List.of(new TelefoneDTO("7799161","Celular")),
                new EnderecoDTO("Rua b","Zezinho",cidade,uf,"Brasil","4956872131")
                );
//        f.setNome(nome);
//        f.setIdade(idade);
//        f.setTipo(TipoContratacao.valueOf(tipo));
//        f.setSalario(new java.math.BigDecimal(salario));
//        f.setIsAtivo(true);
//        f.setDataNascimento(java.time.LocalDate.now().minusYears(idade));
//        f.setFuncao(funcao);
//        f.setSexo(sexo);
//        f.setEscolaridade(escolaridade);
//        // Endereço (Garantindo o vínculo bidirecional)
//        Endereco e = new Endereco();
//        e.setRua("Rua Exemplo, " + idade);
//        e.setBairro("Centro");
//        e.setMunicipio(cidade);
//        e.setEstado(uf);
//        e.setCep("400445");
//        e.setPais("Brasil");
//        e.setFuncionario(f); // FK
//        f.setEndereco(e);
//
//        // Telefone (Garantindo o vínculo bidirecional)
//        Telefone t = new Telefone();
//        t.setNumero("799999900" + idade);
//        t.setTipo("CELULAR");
//        t.setFuncionario(f); // FK
//        f.setTelefones(List.of(t));

        return f;
    }
}
