package br.org.generation.farmacia.repository;

import org.springframework.stereotype.Repository;

import br.org.generation.farmacia.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findAllByMarcaContainingIgnoreCase(@Param("marca") String marca);
	

}
