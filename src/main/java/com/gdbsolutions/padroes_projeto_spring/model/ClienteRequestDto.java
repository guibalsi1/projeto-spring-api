package com.gdbsolutions.padroes_projeto_spring.model;

/**
 * DTO for client creation requests.
 * This DTO accepts the required fields (cpfNumber, birthDate, cep) 
 * to build a complete client payload.
 */
public class ClienteRequestDto {
    
    private String cpfNumber;
    private String birthDate;
    private String cep;
    private String nome; // Optional - can be fetched from CPF API

    public String getCpfNumber() {
        return cpfNumber;
    }

    public void setCpfNumber(String cpfNumber) {
        this.cpfNumber = cpfNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}