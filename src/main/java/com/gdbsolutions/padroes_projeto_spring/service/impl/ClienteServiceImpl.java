package com.gdbsolutions.padroes_projeto_spring.service.impl;

import com.gdbsolutions.padroes_projeto_spring.model.*;
import com.gdbsolutions.padroes_projeto_spring.service.ClienteService;
import com.gdbsolutions.padroes_projeto_spring.service.CpfService;
import com.gdbsolutions.padroes_projeto_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author falvojr
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    private CpfService cpfService;
    @Autowired
    private CPFRepository cpfRepository;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteCompleto(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteCompleto(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            clienteRepository.deleteById(id);
        }
    }

    private void salvarClienteCompleto(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        String cpf = cliente.getCpf();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        ApiCpfResponse cpfBd = cpfRepository.findById(cpf).orElseGet(() -> {
            ApiCpfResponse novoCpf = cpfService.consultarCpf(cpf);
            cpfRepository.save(novoCpf);
            return novoCpf;
        });
        cliente.setEndereco(endereco);
        cliente.setCpfDados(cpfBd);
        cliente.setNome(cpfBd.getData().getName());

        clienteRepository.save(cliente);
    }

}