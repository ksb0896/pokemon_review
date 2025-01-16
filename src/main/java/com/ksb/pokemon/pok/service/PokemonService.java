package com.ksb.pokemon.pok.service;

import com.ksb.pokemon.pok.dto.PokemonDto;
import com.ksb.pokemon.pok.dto.PokemonResponse;

public interface PokemonService {

	//POST
	PokemonDto createPokemon(PokemonDto pokemonDto);
	
	//GET ALL
	PokemonResponse getAllPokemon(int pageNo, int pageSize);
	
	//GET BY ID
	PokemonDto getPokemonById(int id);
	
	//UPDATE BY ID
	PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
	
	//DELETE BY ID
	void deletePokemonId(int id);
}
