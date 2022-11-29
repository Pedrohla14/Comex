package br.com.alura.comex.controller;

import br.com.alura.comex.controller.model.RelatorioPedidoCategoria;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(@Lazy CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid Categoria categoria) {
        return created(fromCurrentRequestUri()
                .pathSegment(categoriaService.salvar(categoria).getId().toString())
                .build()
                .toUri()
        ).body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok().body(categoriaService.findall());
    }

    @Cacheable(value="RelatorioPedidoCategoria")
    @GetMapping(value = "pedidos")
    public List<RelatorioPedidoCategoria> findByCategoria() {
        return categoriaService.findByCategoria();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoriaService.findById(id));
    }
}
