package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EnderecoClienteService {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public EnderecoCliente save(EnderecoCliente enderecoCliente) {

        if (enderecoCliente.getCliente() == null || enderecoCliente.getCliente().getId() == null) {
            throw new RuntimeException("Cliente deve ser informado.");
        }

        // Busca o cliente do banco para garantir entidade gerenciada
        Cliente cliente = clienteRepository.findById(enderecoCliente.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        enderecoCliente.setCliente(cliente);

        enderecoCliente.setHabilitado(Boolean.TRUE);
        enderecoCliente.setDataCriacao(LocalDate.now());

        return enderecoClienteRepository.save(enderecoCliente);
    }

    public List<EnderecoCliente> buscarPorClienteId(Long clienteId) {
        return enderecoClienteRepository.findByClienteId(clienteId);
    }

    public List<EnderecoCliente> buscarPorCliente(Long idCliente) {
        return enderecoClienteRepository.findByClienteId(idCliente);
    }

    public List<EnderecoCliente> listarTodos() {
        return enderecoClienteRepository.findAll();
    }

    public EnderecoCliente obterPorID(Long id) {
        return enderecoClienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Long id, EnderecoCliente enderecoAlterado) {
        EnderecoCliente endereco = enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com id: " + id));

        endereco.setEndereco(enderecoAlterado.getEndereco());
        endereco.setNumero(enderecoAlterado.getNumero());
        endereco.setComplemento(enderecoAlterado.getComplemento());
        endereco.setBairro(enderecoAlterado.getBairro());
        endereco.setCidade(enderecoAlterado.getCidade());
        endereco.setUf(enderecoAlterado.getUf());
        endereco.setCep(enderecoAlterado.getCep());

        // Corrige aqui para buscar o cliente gerenciado antes de setar
        if (enderecoAlterado.getCliente() != null && enderecoAlterado.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(enderecoAlterado.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Cliente não encontrado com id: " + enderecoAlterado.getCliente().getId()));
            endereco.setCliente(cliente);
        }

        enderecoClienteRepository.save(endereco);
    }

    @Transactional
    public void delete(Long id) {
        EnderecoCliente endereco = enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com id: " + id));

        endereco.setHabilitado(Boolean.FALSE);
        enderecoClienteRepository.save(endereco);
    }
}