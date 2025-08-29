package com.gdbsolutions.padroes_projeto_spring.service;


import com.gdbsolutions.padroes_projeto_spring.controller.FeingConfig;
import com.gdbsolutions.padroes_projeto_spring.model.ApiCpfResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpf", url = "https://api.cpfhub.io/api/cpf", configuration = FeingConfig.class)
public interface CpfService {
    @GetMapping("/{cpf}/json")
    ApiCpfResponse consultarCpf(@PathVariable("cpf") String cpf);
}
