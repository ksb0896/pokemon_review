package com.ksb.pokemon.pok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksb.pokemon.pok.models.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

}
