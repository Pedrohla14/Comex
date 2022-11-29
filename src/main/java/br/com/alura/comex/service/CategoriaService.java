package br.com.alura.comex.service;

import br.com.alura.comex.controller.model.RelatorioPedidoCategoria;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final PedidoService pedidoService;

    public CategoriaService(@Lazy CategoriaRepository categoriaRepository,
                            @Lazy PedidoService pedidoService) {
        this.categoriaRepository = categoriaRepository;
        this.pedidoService = pedidoService;
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> {
            throw new NoResultException("Ops! A Categoria não foi encontrada. :(");
        });
    }

    public List<Categoria> findall() {
        return categoriaRepository.findAll();
    }

    //@Cacheable(value = "RelatorioPedidoCategoria", key = "#p0")
    public List<RelatorioPedidoCategoria> findByCategoria() {
        return pedidoService.getRelatorio();
    }
}
