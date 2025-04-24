package com.controlePokemon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controlePokemon.entities.Pokemon;
import com.controlePokemon.repository.PokemonRepository;

@Service
public class PokemonService {
	private final PokemonRepository pokemonRepository;
	
	public PokemonService(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}
	
	public List<Pokemon> getAllPokemon() {
		return pokemonRepository.findAll();
	}
	
	public Pokemon getPokemonById(Long id) {
		return pokemonRepository.findById(id).orElse(null);
	}
	
	public Pokemon savePokemon(Pokemon pokemon) {
		return pokemonRepository.save(pokemon);
	}
	
	public Pokemon alteraPokemon(Long id, Pokemon pokemon) {
		Optional<Pokemon>existePokemon = pokemonRepository.findById(id);
		if(existePokemon.isPresent()) {
			pokemon.setId(id);
			return pokemonRepository.save(pokemon);
		}return null;
	}
	
	public boolean deletePokemon(Long id) {
		Optional<Pokemon>existePokemon = pokemonRepository.findById(id);
		if(existePokemon.isPresent()) {
			pokemonRepository.deleteById(id);
			return true;
		}return false;
	}
}
