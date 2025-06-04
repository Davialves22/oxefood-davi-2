package br.com.ifpe.oxefood.api.cupomDesconto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cupom.CupomDesconto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CupomDescontoRequest { // vai converter num objeto que tenha os atributos

  private String codigoDesconto;

  private Double percentualDesconto;

  private Double valorDesconto;

  private Double valorMinimoPermitido;

  private Integer quantidadeMaximaUso;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate inicioVigencia;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate fimVigencia;

  public CupomDesconto build() {

    return CupomDesconto.builder()
        .codigoDesconto(codigoDesconto)
        .percentualDesconto(percentualDesconto)
        .valorDesconto(valorDesconto)
        .valorMinimoPermitido(valorMinimoPermitido)
        .quantidadeMaximaUso(quantidadeMaximaUso)
        .inicioVigencia(inicioVigencia)
        .fimVigencia(fimVigencia)
        .build();
  }

}
