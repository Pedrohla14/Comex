package br.com.alura.comex.repository;

import br.com.alura.comex.controller.model.ClienteDTO;
import br.com.alura.comex.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT new br.com.alura.comex.controller.model.ClienteDTO(c.nome, c.cpf, c.telefone, concat( c.cidade, '-', c.estado)) FROM Cliente c ")
    Page<ClienteDTO> findClientes(Pageable pageable);

}
