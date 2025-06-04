package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  @Transactional // escopo de transação no banco de dados
  public Cliente save(Cliente cliente) {

    cliente.setHabilitado(Boolean.TRUE);
    return repository.save(cliente);
  }

  public List<Cliente> listarTodos() {

    return repository.findAll(); // select * from cliente
  }

  public Cliente obterPorID(Long id) {

    return repository.findById(id).get(); // select * from cliente where id= ?
  }

  @Transactional
  public void update(Long id, Cliente clienteAlterado) {
    Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    cliente.setEnderecos(clienteAlterado.getEnderecos());
    cliente.setNome(clienteAlterado.getNome());
    cliente.setDataNascimento(clienteAlterado.getDataNascimento());
    cliente.setCpf(clienteAlterado.getCpf());
    cliente.setFoneCelular(clienteAlterado.getFoneCelular());
    cliente.setFoneFixo(clienteAlterado.getFoneFixo());

    // Atualiza endereços garantindo a referência para o cliente
    if (clienteAlterado.getEnderecos() != null) {
      clienteAlterado.getEnderecos().forEach(endereco -> endereco.setCliente(cliente));
      cliente.setEnderecos(clienteAlterado.getEnderecos());
    } else {
      cliente.getEnderecos().clear(); // limpa os endereços se for null
    }

    cliente.setVersao(cliente.getVersao() + 1);
    repository.save(cliente);
  }

  @Transactional
  public void delete(Long id) {

    Cliente cliente = repository.findById(id).get();
    cliente.setHabilitado(Boolean.FALSE);

    repository.save(cliente);
  }

}