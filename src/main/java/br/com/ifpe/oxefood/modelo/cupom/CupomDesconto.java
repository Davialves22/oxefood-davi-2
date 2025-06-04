package br.com.ifpe.oxefood.modelo.cupom;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // transforma numa classe exisitivel no jpa
@Table(name = "Cupom_De_Desconto") // especifica que a classe sera convertida em tabela
@SQLRestriction("habilitado = true") // acresenta em todas as consultas uma clausula where: where habilidado = true

@Builder // forma de instanciar objetos da classe
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CupomDesconto extends EntidadeAuditavel {

  @Column
  private String codigoDesconto;

  @Column
  private Double percentualDesconto;

  @Column
  private Double valorDesconto;

  @Column
  private Double valorMinimoPermitido;

  @Column
  private Integer quantidadeMaximaUso;

  @Column
  private LocalDate inicioVigencia;

  @Column
  private LocalDate fimVigencia;

}
