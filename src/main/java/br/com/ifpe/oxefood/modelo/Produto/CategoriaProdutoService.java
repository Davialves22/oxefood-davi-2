package br.com.ifpe.oxefood.modelo.Produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @Transactional
    public CategoriaProduto save(CategoriaProduto categoriaProduto) {
        categoriaProduto.setHabilitado(Boolean.TRUE);
        return categoriaProdutoRepository.save(categoriaProduto);
    }

    public List<CategoriaProduto> listarTodos() {
        return categoriaProdutoRepository.findAll();
    }

    public CategoriaProduto obterPorID(Long id) {
        return categoriaProdutoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Long id, CategoriaProduto categoriaProdutoAlterado) {
        CategoriaProduto categoriaProduto = categoriaProdutoRepository.findById(id).get();

        categoriaProduto.setDescricao(categoriaProdutoAlterado.getDescricao());

        categoriaProdutoRepository.save(categoriaProduto);
    }

    @Transactional
    public void delete(Long id) {
        CategoriaProduto categoriaProduto = categoriaProdutoRepository.findById(id).get();
        categoriaProduto.setHabilitado(Boolean.FALSE);

        categoriaProdutoRepository.save(categoriaProduto);
    }
}
