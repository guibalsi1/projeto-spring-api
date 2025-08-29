package com.gdbsolutions.padroes_projeto_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiCpfResponse {
    @OneToOne
    private CpfDto data;
    @Id
    private String cpf = data.getCpfNumber();
    public CpfDto getData() {
        return data;
    }

    public void setData(CpfDto data) {
        this.data = data;
    }
}
