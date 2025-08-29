package com.gdbsolutions.padroes_projeto_spring.model;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    private String cpf;
    private String nome;
    @ManyToOne
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    private ApiCpfResponse cpfDados;

    public ApiCpfResponse getCpfDados() {
        return cpfDados;
    }

    public void setCpfDados(ApiCpfResponse cpfDados) {
        this.cpfDados = cpfDados;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}