package br.com.alura.comex.controller;


import br.com.alura.comex.controller.model.ProdutoDTO;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.ProdutoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(@Lazy ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto) {
        return created(fromCurrentRequestUri()
                .pathSegment(produtoService.salvarOuAtualizar(produto).getId().toString())
                .build()
                .toUri()
        ).body(produto);
    }

    @GetMapping("paginado")
    public Page<Produto> paginacao(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return produtoService.findallPage(pageable);
    }

    @GetMapping("lista")
    public List<Produto> lista() {
        return produtoService.findall();
    }

    @GetMapping(value = "{id}")
    public ProdutoDTO findById(@PathVariable Long id) {
        return produtoService.findProdutoDTOById(id);
    }

    @PutMapping(value = "atualizar/{id}")
    public ResponseEntity produtoAtualizar(@PathVariable Long id, @RequestBody Produto produto) {
        if (!Objects.equals(id, produto.getId())) {
            return (ResponseEntity) ResponseEntity.badRequest();
        } else {
            return ResponseEntity.ok(produtoService.salvarOuAtualizar(produto));
        }
    }
}
