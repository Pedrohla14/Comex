package br.com.alura.comex.controller;

import br.com.alura.comex.controller.model.ClienteDTO;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClientesService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClientesService clientesService;

    public ClienteController(@Lazy ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        return created(fromCurrentRequestUri()
                .pathSegment(clientesService.salvar(cliente).getId().toString())
                .build()
                .toUri()
        ).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok().body(clientesService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientesService.findById(id));
    }

    @GetMapping(value="paginado")
    public ResponseEntity<Page<ClienteDTO>> paginar(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(clientesService.findClientes(pageable));
    }
}
