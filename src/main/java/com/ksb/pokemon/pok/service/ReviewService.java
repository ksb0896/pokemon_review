package com.ksb.pokemon.pok.service;

import java.util.List;

import com.ksb.pokemon.pok.dto.ReviewDto;

public interface ReviewService {
	 ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
	 List<ReviewDto> getReviewsByPokemonId(int id);
	    ReviewDto getReviewById(int reviewId, int pokemonId);
	    ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto);
	    void deleteReview(int pokemonId, int reviewId);
}
