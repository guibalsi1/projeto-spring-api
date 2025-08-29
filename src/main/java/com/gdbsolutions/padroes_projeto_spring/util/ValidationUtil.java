package com.gdbsolutions.padroes_projeto_spring.util;

/**
 * Utility class for validating input data
 */
public class ValidationUtil {
    
    /**
     * Validates if CPF format is correct (11 digits)
     */
    public static boolean isValidCpf(String cpf) {
        if (cpf == null) return false;
        // Remove any non-digit characters
        String cleanCpf = cpf.replaceAll("[^0-9]", "");
        return cleanCpf.length() == 11;
    }
    
    /**
     * Validates if CEP format is correct (8 digits)
     */
    public static boolean isValidCep(String cep) {
        if (cep == null) return false;
        // Remove any non-digit characters
        String cleanCep = cep.replaceAll("[^0-9]", "");
        return cleanCep.length() == 8;
    }
    
    /**
     * Cleans CPF removing any non-digit characters
     */
    public static String cleanCpf(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("[^0-9]", "");
    }
    
    /**
     * Cleans CEP removing any non-digit characters
     */
    public static String cleanCep(String cep) {
        if (cep == null) return null;
        return cep.replaceAll("[^0-9]", "");
    }
}