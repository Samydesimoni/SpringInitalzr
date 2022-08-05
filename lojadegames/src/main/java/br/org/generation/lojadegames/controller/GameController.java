package br.org.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.lojadegames.model.Game;
import br.org.generation.lojadegames.repository.CategoriaRepository;
import br.org.generation.lojadegames.repository.GameRepository;



@RestController
@RequestMapping("tb_games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Game>> getAll(){
        return ResponseEntity.ok(gameRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Game> getById(@PathVariable Long id){
		return gameRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Game>> getByMarca(@PathVariable String marca){
		return ResponseEntity.ok(gameRepository.findAllByMarcaContainingIgnoreCase(marca));
		
	}
	@PostMapping
	public ResponseEntity<Game> post(@Valid @RequestBody Game game){
		if (gameRepository.existsById(game.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(gameRepository.save(game));
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<Game> put(@Valid @RequestBody Game game){
		if (gameRepository.existsById(game.getId())){
			
			if (categoriaRepository.existsById(game.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(gameRepository.save(game));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Game> produto = gameRepository.findById(id);
		
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		gameRepository.deleteById(id);				
	}

}
