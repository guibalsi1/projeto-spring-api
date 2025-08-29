package com.gdbsolutions.padroes_projeto_spring;

import com.gdbsolutions.padroes_projeto_spring.model.ClienteRequestDto;
import com.gdbsolutions.padroes_projeto_spring.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the improved Cliente API.
 * Tests the core functionality of creating clients with CPF, birthDate, and CEP.
 */
@SpringBootTest
public class ClienteApiIntegrationTestSimple {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testCriarClienteComDadosCompletos() {
        ClienteRequestDto request = new ClienteRequestDto();
        request.setCpfNumber("11144477735");
        request.setBirthDate("1985-10-15");
        request.setCep("01310100");
        request.setNome("João Silva");

        var cliente = clienteService.criarClienteComDados(request);

        assertNotNull(cliente);
        assertEquals("11144477735", cliente.getCpf());
        assertEquals("João Silva", cliente.getNome());
        assertEquals("01310100", cliente.getEndereco().getCep());
        assertNotNull(cliente.getCpfDados());
        assertEquals("11144477735", cliente.getCpfDados().getCpf());
    }

    @Test
    public void testCriarClienteSemNome() {
        ClienteRequestDto request = new ClienteRequestDto();
        request.setCpfNumber("12345678901");
        request.setBirthDate("1990-05-20");
        request.setCep("20040020");

        var cliente = clienteService.criarClienteComDados(request);

        assertNotNull(cliente);
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("Nome não informado", cliente.getNome());
        assertEquals("20040020", cliente.getEndereco().getCep());
    }

    @Test
    public void testBuscarClientePorCpf() {
        // First create a client
        ClienteRequestDto request = new ClienteRequestDto();
        request.setCpfNumber("99988877766");
        request.setBirthDate("1980-12-25");
        request.setCep("30112000");
        request.setNome("Maria Santos");

        clienteService.criarClienteComDados(request);

        // Then retrieve it by CPF
        var cliente = clienteService.buscarPorId("99988877766");
        
        assertNotNull(cliente);
        assertEquals("99988877766", cliente.getCpf());
        assertEquals("Maria Santos", cliente.getNome());
    }

    @Test
    public void testListarTodosClientes() {
        var clientes = clienteService.buscarTodos();
        assertNotNull(clientes);
    }
}