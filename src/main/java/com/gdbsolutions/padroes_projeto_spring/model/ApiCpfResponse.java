package com.gdbsolutions.padroes_projeto_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiCpfResponse {
    @OneToOne(cascade = CascadeType.ALL)
    private CpfDto data;
    @Id
    private String cpf;
    public CpfDto getData() {
        return data;
    }

    public void setData(CpfDto data) {
        this.data = data;
        // Set the cpf field when data is set
        if (data != null) {
            this.cpf = data.getCpfNumber();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
