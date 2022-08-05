package br.org.generation.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.org.generation.lojadegames.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	List<Categoria> findAllByQualidadeContainingIgnoreCase(@Param("qualidade") String qualidade); 
}

