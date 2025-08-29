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
    public Cliente buscarPorId(String id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteCompleto(cliente);
    }

    @Override
    public void atualizar(String id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteCompleto(cliente);
        }
    }

    @Override
    public void deletar(String id) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            clienteRepository.deleteById(id);
        }
    }

    private void salvarClienteCompleto(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        String cpf = cliente.getCpf();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            try {
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            } catch (Exception e) {
                // If external API fails, create a minimal address
                Endereco fallbackEndereco = new Endereco();
                fallbackEndereco.setCep(cep);
                fallbackEndereco.setLogradouro("Endereço não encontrado");
                enderecoRepository.save(fallbackEndereco);
                return fallbackEndereco;
            }
        });
        
        ApiCpfResponse cpfBd = cpfRepository.findById(cpf).orElseGet(() -> {
            try {
                ApiCpfResponse novoCpf = cpfService.consultarCpf(cpf);
                cpfRepository.save(novoCpf);
                return novoCpf;
            } catch (Exception e) {
                // If external API fails, create minimal CPF data
                ApiCpfResponse fallbackCpf = new ApiCpfResponse();
                CpfDto cpfDto = new CpfDto();
                cpfDto.setCpfNumber(cpf);
                cpfDto.setName(cliente.getNome() != null ? cliente.getNome() : "Nome não informado");
                fallbackCpf.setData(cpfDto);
                fallbackCpf.setCpf(cpf);
                cpfRepository.save(fallbackCpf);
                return fallbackCpf;
            }
        });
        
        cliente.setEndereco(endereco);
        cliente.setCpfDados(cpfBd);
        if (cpfBd.getData() != null && cpfBd.getData().getName() != null) {
            cliente.setNome(cpfBd.getData().getName());
        }

        clienteRepository.save(cliente);
    }

    @Override
    public Cliente criarClienteComDados(ClienteRequestDto clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteRequest.getCpfNumber());
        cliente.setNome(clienteRequest.getNome());
        
        // Create address object with CEP
        Endereco endereco = new Endereco();
        endereco.setCep(clienteRequest.getCep());
        cliente.setEndereco(endereco);
        
        // Save the complete client
        salvarClienteCompleto(cliente);
        
        return cliente;
    }

}