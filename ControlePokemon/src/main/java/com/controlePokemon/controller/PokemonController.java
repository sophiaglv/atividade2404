package com.controlePokemon.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlePokemon.entities.Pokemon;
import com.controlePokemon.service.PokemonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
	private final PokemonService pokemonService;
	
	public PokemonController (PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id){
		Pokemon pokemon = pokemonService.getPokemonById(id);
		if(pokemon != null) {
			return ResponseEntity.ok(pokemon);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Pokemon>>getAllPokemon(){
		List<Pokemon>pokemons = pokemonService.getAllPokemon();
		return ResponseEntity.ok(pokemons);
	}
	
	@PostMapping("/")
	public ResponseEntity<Pokemon>createPokemon(@RequestBody @Valid Pokemon pokemon){
		Pokemon createPokemon = pokemonService.savePokemon(pokemon);
		return ResponseEntity.status(HttpStatus.CREATED).body(createPokemon);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pokemon>alteraPokemon(@PathVariable Long id, @RequestBody @Valid Pokemon pokemon){
		Pokemon existePokemon = pokemonService.getPokemonById(id);
		if(existePokemon != null) {
			return ResponseEntity.ok(pokemon);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deletePokemon(@PathVariable Long id){
		Pokemon existePokemon = pokemonService.getPokemonById(id);
		if(existePokemon != null) {
			pokemonService.deletePokemon(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
