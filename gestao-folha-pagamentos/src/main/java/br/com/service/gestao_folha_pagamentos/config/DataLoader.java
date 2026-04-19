package br.com.service.gestao_folha_pagamentos.config;

import br.com.service.gestao_folha_pagamentos.model.entity.Endereco;
import br.com.service.gestao_folha_pagamentos.model.entity.Funcionario;
import br.com.service.gestao_folha_pagamentos.model.entity.Telefone;
import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import br.com.service.gestao_folha_pagamentos.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("teste")
public class DataLoader implements CommandLineRunner {

    private final FuncionarioRepository repository;

    public DataLoader(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("### Iniciando carga de dados no H2... ###");

        List<Funcionario> funcionarios = List.of(
                criarFuncionario("Ana Silva", 28, "CLT", "8500.00", "Aracaju", "SE","Programador"),
                criarFuncionario("Bruno Costa", 35, "PJ", "12000.00", "Nossa Senhora do Socorro", "SE","Administrativo"),
                criarFuncionario("Carla Souza", 22, "ESTAGIO", "1500.00", "São Cristóvão", "SE", "Administrativo"),
                criarFuncionario("Diego Oliveira", 40, "CLT", "9800.00", "Lagarto", "SE","Marketing"),
                criarFuncionario("Eliana Santos", 31, "CLT", "7200.00", "Itabaiana", "SE","Recepcionista")
        );

        repository.saveAll(funcionarios);
        System.out.println("### " + funcionarios.size() + " Funcionários cadastrados com sucesso! ###");
    }

    private Funcionario criarFuncionario(String nome, Integer idade, String tipo, String salario, String cidade, String uf, String funcao) {
        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setIdade(idade);
        f.setTipo(TipoContratacao.valueOf(tipo));
        f.setSalario(new java.math.BigDecimal(salario));
        f.setIsAtivo(true);
        f.setDataNascimento(java.time.LocalDate.now().minusYears(idade));
        f.setFuncao(funcao);
        // Endereço (Garantindo o vínculo bidirecional)
        Endereco e = new Endereco();
        e.setRua("Rua Exemplo, " + idade);
        e.setBairro("Centro");
        e.setMunicipio(cidade);
        e.setEstado(uf);
        e.setCep("400445");
        e.setPais("Brasil");
        e.setFuncionario(f); // FK
        f.setEndereco(e);

        // Telefone (Garantindo o vínculo bidirecional)
        Telefone t = new Telefone();
        t.setNumero("799999900" + idade);
        t.setTipo("CELULAR");
        t.setFuncionario(f); // FK
        f.setTelefones(List.of(t));

        return f;
    }
}
