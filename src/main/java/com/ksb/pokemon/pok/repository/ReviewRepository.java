package com.ksb.pokemon.pok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ksb.pokemon.pok.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByPokemonId(int pokemonId);
}
