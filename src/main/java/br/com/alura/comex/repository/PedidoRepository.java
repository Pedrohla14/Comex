package br.com.alura.comex.repository;

import br.com.alura.comex.controller.model.RelatorioPedidoCategoria;
import br.com.alura.comex.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT count(p.id) FROM Pedido p WHERE p.cliente.id = :idCliente")
    Long quantidadeComprasPorIdCliente(Long idCliente);

    @Query("SELECT new br.com.alura.comex.controller.model.RelatorioPedidoCategoria(" +
            " ct.nome, " +
            " sum(it.quantidade), " +
            " sum(it.valorComDesconto - p.desconto))" +
            "FROM Pedido p " +
            "INNER JOIN p.itensPedido it " +
            "INNER JOIN it.produto pr " +
            "INNER JOIN pr.categoria ct " +
            "group by ct.nome")
    List<RelatorioPedidoCategoria> getRelatorio();

    @Query("SELECT p FROM Pedido p WHERE p.id = :idPedido")
    Optional<Pedido> getDetalhesPedido(Long idPedido);
}
