package com.gdbsolutions.padroes_projeto_spring.model;

import org.springframework.data.repository.CrudRepository;

public interface CPFRepository extends CrudRepository<ApiCpfResponse, String> {
}
