package com.ksb.pokemon.pok.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksb.pokemon.pok.dto.ReviewDto;
import com.ksb.pokemon.pok.exception.PokemonNotFoundException;
import com.ksb.pokemon.pok.exception.ReviewNotFoundException;
import com.ksb.pokemon.pok.models.Pokemon;
import com.ksb.pokemon.pok.models.Review;
import com.ksb.pokemon.pok.repository.PokemonRepository;
import com.ksb.pokemon.pok.repository.ReviewRepository;
import com.ksb.pokemon.pok.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	private ReviewRepository reviewRepository;
	private PokemonRepository pokemonRepository;

	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
		this.reviewRepository = reviewRepository;
		this.pokemonRepository = pokemonRepository;
	}

	// POST API
	@Override
	public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {

		Review review = mapToEntity(reviewDto);
		Pokemon pokemon = pokemonRepository.findById(pokemonId)
				.orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
		review.setPokemon(pokemon);
		Review newReview = reviewRepository.save(review);

		return mapToDto(newReview);
	}

	// GET API ALL REVIEWS
	@Override
	public List<ReviewDto> getReviewsByPokemonId(int id) {
		List<Review> reviews = reviewRepository.findByPokemonId(id);

		return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
	}

	// GET BY ID
	@Override
	public ReviewDto getReviewById(int reviewId, int pokemonId) {
		Pokemon pokemon = pokemonRepository.findById(pokemonId)
				.orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));

		if (review.getPokemon().getId() != pokemon.getId()) {
			throw new ReviewNotFoundException("This review does not belong to a pokemon");
		}

		return mapToDto(review);
	}

	// UPDATE BY ID
	@Override
	public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
		Pokemon pokemon = pokemonRepository.findById(pokemonId)
				.orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));

		if (review.getPokemon().getId() != pokemon.getId()) {
			throw new ReviewNotFoundException("This review does not belong to a pokemon");
		}

		review.setTitle(reviewDto.getTitle());
		review.setContent(reviewDto.getContent());
		review.setStars(reviewDto.getStars());

		Review updateReview = reviewRepository.save(review);

		return mapToDto(updateReview);
	}

	// DELETE BY ID
	@Override
	public void deleteReview(int pokemonId, int reviewId) {
		Pokemon pokemon = pokemonRepository.findById(pokemonId)
				.orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));

		if (review.getPokemon().getId() != pokemon.getId()) {
			throw new ReviewNotFoundException("This review does not belong to a pokemon");
		}

		reviewRepository.delete(review);
	}

	private ReviewDto mapToDto(Review review) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(review.getId());
		reviewDto.setTitle(review.getTitle());
		reviewDto.setContent(review.getContent());
		reviewDto.setStars(review.getStars());
		return reviewDto;
	}

	private Review mapToEntity(ReviewDto reviewDto) {
		Review review = new Review();
		review.setId(reviewDto.getId());
		review.setTitle(reviewDto.getTitle());
		review.setContent(reviewDto.getContent());
		review.setStars(reviewDto.getStars());
		return review;
	}
}
