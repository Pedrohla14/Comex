package br.com.alura.comex.service;

import br.com.alura.comex.controller.model.ProdutoDTO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Page<Categoria> searchPageable(String searchTerm, Pageable pageable) {
        return repository.searchPageable(searchTerm, pageable);
    }

    public Page<Produto> findallPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Produto> findall() {
        return repository.findAll();
    }

    @Transactional
    public Produto salvarOuAtualizar(Produto produto) {
        return repository.save(produto);
    }

    public ProdutoDTO findProdutoDTOById(Long id) {
        return repository.findProdutoDTOById(id).orElseThrow(() -> {
            throw new NoResultException("Ops! Não foi encontrada a entidade! :(");
        });
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoResultException("Ops! Não foi encontrada a entidade! :(");
        });
    }

    public List<Produto> findProdutosByIds(List<Long> ids) {
        return repository.getProdutoByIds(ids);
    }
}
