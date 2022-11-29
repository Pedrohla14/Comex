package br.com.alura.comex.service;

import br.com.alura.comex.controller.model.DetalhesPedidoDTO;
import br.com.alura.comex.controller.model.ItemProdutoDTO;
import br.com.alura.comex.controller.model.PedidoDTO;
import br.com.alura.comex.controller.model.RelatorioPedidoCategoria;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ItemPedidoRepository;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static br.com.alura.comex.controller.model.DetalhesPedidoDTO.getDetalhesPedidoFromPedido;
import static io.vavr.collection.List.ofAll;

@Service
public class PedidoService {

    private final ProdutoService produtoService;

    private final ClientesService clientesService;

    private final PedidoRepository pedidoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(@Lazy ProdutoService produtoService,
                         @Lazy ClientesService clientesService,
                         @Lazy PedidoRepository pedidoRepository,
                         @Lazy ItemPedidoRepository itemPedidoRepository) {
        this.produtoService = produtoService;
        this.clientesService = clientesService;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        return pedidoRepository.save(new Pedido(clientesService.findById(pedidoDTO.getIdCliente()),
                getItensDePedidoFromPeditoDTO(pedidoDTO.getItemProdutos()), quantidadePedidosPorCliente(pedidoDTO.getIdCliente())));
    }

    private List<ItemDePedido> getItensDePedidoFromPeditoDTO(List<ItemProdutoDTO> itens) {
        List<Produto> produtos = produtoService.findProdutosByIds(ofAll(itens).map(ItemProdutoDTO::getIdProduto).toJavaList());

        return ofAll(itens)
                .map(item -> {
                    Produto produto = ofAll(produtos).filter(p -> Objects.equals(p.getId(), item.getIdProduto())).last();
                    return new ItemDePedido(item.getQuantidade(), produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade()));
                })
                .toJavaList();
    }

    public Long quantidadePedidosPorCliente(Long idCliente) {
        return pedidoRepository.quantidadeComprasPorIdCliente(idCliente);
    }

    public List<Pedido> findall() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> {
            throw new NoResultException("Ops! Pedido não encontrado. :(");
        });
    }

    public List<RelatorioPedidoCategoria> getRelatorio() {
        return pedidoRepository.getRelatorio();
    }

    public DetalhesPedidoDTO getDetalhesPedido(Long id) {
        return getDetalhesPedidoFromPedido(pedidoRepository.getDetalhesPedido(id).orElseThrow(() -> {
            throw new NoResultException("Ops! O Pedido não encontrado. :(");
        }));
    }

    public List<ItemDePedido> getItensPedidosFromIdPedido(Long idPedido) {
        return itemPedidoRepository.findByPedido_Id(idPedido);
    }
}
