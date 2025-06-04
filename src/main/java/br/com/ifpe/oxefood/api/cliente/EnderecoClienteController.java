package br.com.ifpe.oxefood.api.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enderecocliente")
@CrossOrigin
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteService enderecoClienteService;

    @PostMapping
    public ResponseEntity<EnderecoCliente> save(@RequestBody EnderecoCliente enderecoCliente) {
        EnderecoCliente salvo = enderecoClienteService.save(enderecoCliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<EnderecoCliente> listarTodos() {
        return enderecoClienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoCliente> obterPorID(@PathVariable Long id) {
        EnderecoCliente endereco = enderecoClienteService.obterPorID(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid EnderecoClienteRequest request) {
        EnderecoCliente enderecoAtualizado = request.toEntity();
        enderecoClienteService.update(id, enderecoAtualizado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoClienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}