package com.gdbsolutions.padroes_projeto_spring.controller;

import com.gdbsolutions.padroes_projeto_spring.model.Cliente;
import com.gdbsolutions.padroes_projeto_spring.model.ClienteRequestDto;
import com.gdbsolutions.padroes_projeto_spring.service.ClienteService;
import com.gdbsolutions.padroes_projeto_spring.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 *
 * @author falvojr
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteRequestDto clienteRequest) {
        // Validate input
        if (!ValidationUtil.isValidCpf(clienteRequest.getCpfNumber())) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
        }
        if (!ValidationUtil.isValidCep(clienteRequest.getCep())) {
            throw new IllegalArgumentException("CEP deve conter 8 dígitos");
        }
        
        // Clean the input data
        clienteRequest.setCpfNumber(ValidationUtil.cleanCpf(clienteRequest.getCpfNumber()));
        clienteRequest.setCep(ValidationUtil.cleanCep(clienteRequest.getCep()));
        
        Cliente cliente = clienteService.criarClienteComDados(clienteRequest);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable String id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
