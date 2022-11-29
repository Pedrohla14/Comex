package br.com.alura.comex.repository;

import br.com.alura.comex.model.ItemDePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemDePedido, Long> {

    List<ItemDePedido> findByPedido_Id(Long idPedido);
}
