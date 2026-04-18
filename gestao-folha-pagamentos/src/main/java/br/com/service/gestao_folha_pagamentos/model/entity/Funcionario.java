package br.com.service.gestao_folha_pagamentos.model.entity;

import br.com.service.gestao_folha_pagamentos.model.enumerated.TipoContratacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Table(name = "funcionario")
@Entity()

public class Funcionario extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "sexo", length = 1)
    private Character sexo;

    @Column(name = "escolaridade", length = 100)
    private String escolaridade;

    @Column(name = "funcao", nullable = false, length = 100)
    private String funcao;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "isAtivo", nullable = false)
    private Boolean isAtivo = true;
    @Column(name = "data_desligamento")
    private LocalDate dataDesligamento;

    @Enumerated(EnumType.STRING)
    private TipoContratacao tipo;

    /**
     * Vou deixar pelo hibernateAtualizar e vou usar a técnica de dirty-checking
     * **/
    @OneToMany(mappedBy = "funcionario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Boolean getIsAtivo() {
        return isAtivo;
    }

    public TipoContratacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoContratacao tipo) {
        this.tipo = tipo;
    }

    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(LocalDate dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(idade, that.idade) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(sexo, that.sexo) && Objects.equals(escolaridade, that.escolaridade) && Objects.equals(funcao, that.funcao) && Objects.equals(salario, that.salario) && Objects.equals(isAtivo, that.isAtivo) && Objects.equals(dataDesligamento, that.dataDesligamento) && tipo == that.tipo && Objects.equals(telefones, that.telefones) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, dataNascimento, sexo, escolaridade, funcao, salario, isAtivo, dataDesligamento, tipo, telefones, endereco);
    }
}
