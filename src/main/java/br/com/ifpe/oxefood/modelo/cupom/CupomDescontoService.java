package br.com.ifpe.oxefood.modelo.cupom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.cliente.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class CupomDescontoService {

  @Autowired
  private CupomDescontoRepository repository;

  @Transactional // escopo de transação no banco de dados
  public CupomDesconto save(CupomDesconto cupomDesconto) {

    cupomDesconto.setHabilitado(Boolean.TRUE);
    return repository.save(cupomDesconto);
  }

  public List<CupomDesconto> listarTodos() {

    return repository.findAll(); // select * from cliente
  }

  public CupomDesconto obterPorID(Long id) {

    return repository.findById(id).get(); // select * from cliente where id = ?
  }

  @Transactional
  public void update(Long id, CupomDesconto cupomDescontoAlterado) {

    CupomDesconto cupomDesconto = repository.findById(id).get();

    cupomDesconto.setCodigoDesconto(cupomDescontoAlterado.getCodigoDesconto());
    cupomDesconto.setPercentualDesconto(cupomDescontoAlterado.getPercentualDesconto());
    cupomDesconto.setValorDesconto(cupomDescontoAlterado.getValorDesconto());
    cupomDesconto.setValorMinimoPermitido(cupomDescontoAlterado.getValorMinimoPermitido());
    cupomDesconto.setQuantidadeMaximaUso(cupomDescontoAlterado.getQuantidadeMaximaUso());

    cupomDesconto.setInicioVigencia(cupomDescontoAlterado.getInicioVigencia());
    cupomDesconto.setFimVigencia(cupomDescontoAlterado.getFimVigencia());

    repository.save(cupomDesconto);
  }

  @Transactional
  public void delete(Long id) {

    CupomDesconto cupomDesconto = repository.findById(id).get();
    cupomDesconto.setHabilitado(Boolean.FALSE);

    repository.save(cupomDesconto);
  }

}
