package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;
import java.util.List;

import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest { // vai converter num objeto que tenha os atributos

  private List<Long> idEnderecos;

  @NotNull(message = "O Nome é de preenchimento obrigatório")
  @NotEmpty(message = "O Nome é de preenchimento obrigatório")
  @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
  private String nome;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataNascimento;

  @NotBlank(message = "O CPF é de preenchimento obrigatório")
  @CPF
  private String cpf;

  @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
  private String foneCelular;

  private String foneFixo;

  public Cliente toEntity(List<EnderecoCliente> enderecos) {
    Cliente cliente = Cliente.builder()
            .nome(nome)
            .dataNascimento(dataNascimento)
            .cpf(cpf)
            .foneCelular(foneCelular)
            .foneFixo(foneFixo)
            .build();

    if (enderecos != null && !enderecos.isEmpty()) {
      enderecos.forEach(e -> e.setCliente(cliente));
      cliente.setEnderecos(enderecos);
    }

    return cliente;
  }
}