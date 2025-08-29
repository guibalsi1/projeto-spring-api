package com.gdbsolutions.padroes_projeto_spring.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpfDto {
    private String name;
    private String status;
    private String situation;
    private String birthDate;
    private String deathYear;
    @Id
    private String cpfNumber;
    private String registrationDate;
    private String verificationDigit;
    @OneToOne(cascade = CascadeType.DETACH)
    private ReceiptDto receipt;
    private String validationUrl;
    private String validationHtmlUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(String deathYear) {
        this.deathYear = deathYear;
    }

    public String getCpfNumber() {
        return cpfNumber;
    }

    public void setCpfNumber(String cpfNumber) {
        this.cpfNumber = cpfNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVerificationDigit() {
        return verificationDigit;
    }

    public void setVerificationDigit(String verificationDigit) {
        this.verificationDigit = verificationDigit;
    }

    public ReceiptDto getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptDto receipt) {
        this.receipt = receipt;
    }

    public String getValidationUrl() {
        return validationUrl;
    }

    public void setValidationUrl(String validationUrl) {
        this.validationUrl = validationUrl;
    }

    public String getValidationHtmlUrl() {
        return validationHtmlUrl;
    }

    public void setValidationHtmlUrl(String validationHtmlUrl) {
        this.validationHtmlUrl = validationHtmlUrl;
    }
}

