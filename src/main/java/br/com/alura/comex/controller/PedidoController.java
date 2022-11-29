package br.com.alura.comex.controller;

import br.com.alura.comex.controller.model.DetalhesPedidoDTO;
import br.com.alura.comex.controller.model.PedidoDTO;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.retention.BloquearAcessoPedidoDeOutrosUsuarios;
import br.com.alura.comex.service.PedidoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(@Lazy PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return created(fromCurrentRequestUri().pathSegment(
                        pedido.getId().toString())
                .build()
                .toUri()
        ).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.ok().body(pedidoService.findall());
    }

    @GetMapping(value = "{id}")
    @BloquearAcessoPedidoDeOutrosUsuarios
    public ResponseEntity<DetalhesPedidoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.getDetalhesPedido(id));
    }

    @GetMapping(value = "itens-pedido/{id}")
    @BloquearAcessoPedidoDeOutrosUsuarios
    public ResponseEntity<List<ItemDePedido>> findItensPedidoFromIdPedido(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.getItensPedidosFromIdPedido(id));
    }
}
