package br.com.ifpe.oxefood.api.cliente;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoClienteRequest {

    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 150)
    private String endereco;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10)
    private String numero;

    @Size(max = 30)
    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 60)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 60)
    private String cidade;

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotBlank(message = "UF é obrigatório")
    @Size(min = 2, max = 2)
    private String uf;

    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 9)
    private String cep;

    public EnderecoCliente toEntity() {

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);

        return EnderecoCliente.builder()
                .endereco(endereco)
                .numero(numero)
                .complemento(complemento)
                .bairro(bairro)
                .cidade(cidade)
                .uf(uf)
                .cep(cep)
                .cliente(cliente)
                .build();
    }
}