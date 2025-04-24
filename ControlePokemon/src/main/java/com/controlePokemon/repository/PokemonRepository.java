package com.controlePokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlePokemon.entities.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}
