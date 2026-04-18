package br.com.service.gestao_folha_pagamentos.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Table(name = "telefone")
@Entity
public class Telefone extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String tipo;
    private String numero;

    @ManyToOne()
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(id, telefone.id) && Objects.equals(tipo, telefone.tipo) && Objects.equals(numero, telefone.numero) && Objects.equals(funcionario, telefone.funcionario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, numero, funcionario);
    }
}
