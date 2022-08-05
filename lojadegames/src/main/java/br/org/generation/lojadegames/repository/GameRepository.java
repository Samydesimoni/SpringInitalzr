package br.org.generation.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.org.generation.lojadegames.model.Game;


public interface GameRepository extends JpaRepository<Game, Long> {
	List<Game> findAllByMarcaContainingIgnoreCase(@Param("marca") String marca); 

}
