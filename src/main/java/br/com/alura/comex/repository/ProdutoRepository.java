package br.com.alura.comex.repository;

import br.com.alura.comex.controller.model.ProdutoDTO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("FROM Produto b " +
            "WHERE :searchTerm is null or LOWER(b.descricao) like %:searchTerm% " +
            "OR cast(b.id as string) = :searchTerm")
    Page<Categoria> searchPageable(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT new br.com.alura.comex.controller.model.ProdutoDTO(p.id, p.descricao) FROM Produto p where p.id = :id")
    Optional<ProdutoDTO> findProdutoDTOById(@Param("id") Long id);

    @Query("FROM Produto  p where p.id in :ids")
    List<Produto> getProdutoByIds(List<Long> ids);
}
