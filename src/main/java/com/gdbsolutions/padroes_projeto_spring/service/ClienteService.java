package com.gdbsolutions.padroes_projeto_spring.service;

import com.gdbsolutions.padroes_projeto_spring.model.Cliente;
import com.gdbsolutions.padroes_projeto_spring.model.ClienteRequestDto;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author falvojr
 */
public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(String id);

    void inserir(Cliente cliente);

    void atualizar(String id, Cliente cliente);

    void deletar(String id);

    Cliente criarClienteComDados(ClienteRequestDto clienteRequest);

}
