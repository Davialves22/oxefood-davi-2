package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {

    List<EnderecoCliente> findByCliente(Cliente cliente);

}