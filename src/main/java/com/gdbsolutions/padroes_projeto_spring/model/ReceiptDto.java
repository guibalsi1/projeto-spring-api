package com.gdbsolutions.padroes_projeto_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiptDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String emissionTime;
    private String emissionDate;
    private String controlCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmissionTime() {
        return emissionTime;
    }

    public void setEmissionTime(String emissionTime) {
        this.emissionTime = emissionTime;
    }

    public String getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(String emissionDate) {
        this.emissionDate = emissionDate;
    }

    public String getControlCode() {
        return controlCode;
    }

    public void setControlCode(String controlCode) {
        this.controlCode = controlCode;
    }
}
