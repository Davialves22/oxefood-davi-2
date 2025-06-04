package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        // Busca o cliente do banco
        Cliente cliente = clienteRepository.findById(enderecoCliente.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Seta o cliente completo
        enderecoCliente.setCliente(cliente);

        enderecoCliente.setHabilitado(Boolean.TRUE);
        enderecoCliente.setDataCriacao(LocalDate.now());

        return enderecoClienteRepository.save(enderecoCliente);
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

        // Se quiser garantir consistência no cliente, pode adicionar:
        if (enderecoAlterado.getCliente() != null) {
            endereco.setCliente(enderecoAlterado.getCliente());
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