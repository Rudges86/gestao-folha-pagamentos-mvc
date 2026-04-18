package br.com.service.gestao_folha_pagamentos.model.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
@Table(name = "Endereco")
@Entity
public class Endereco extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "rua", length = 200, nullable = false)
    private String rua;

    @Column(name = "bairro", length = 200, nullable = false)
    private String bairro;
    @Column(name = "municipio", length = 200, nullable = false)
    private String municipio;

    @Column(name = "estado", length = 200, nullable = false)
    private String estado;

    @Column(name = "pais", length = 200, nullable = false)
    private String pais;

    @Column(name = "cep",nullable = false)
    private String cep;

    @OneToOne()
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;


    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(rua, endereco.rua) && Objects.equals(bairro, endereco.bairro) && Objects.equals(municipio, endereco.municipio) && Objects.equals(estado, endereco.estado) && Objects.equals(pais, endereco.pais) && Objects.equals(cep, endereco.cep) && Objects.equals(funcionario, endereco.funcionario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rua, bairro, municipio, estado, pais, cep, funcionario);
    }
}
