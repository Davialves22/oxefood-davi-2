package br.com.ifpe.oxefood.modelo.cliente;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "EnderecoCliente")
@SQLRestriction("habilitado = true")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoCliente extends EntidadeAuditavel {

    @Column(nullable = false, length = 150)
    private String endereco;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(length = 30)
    private String complemento;

    @Column(nullable = false, length = 60)
    private String bairro;

    @Column(nullable = false, length = 60)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String uf;

    @Column(nullable = false, length = 9)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;
}