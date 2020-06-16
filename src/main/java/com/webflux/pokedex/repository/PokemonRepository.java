package com.webflux.pokedex.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflux.pokedex.model.Pokemon;

public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String>{
}
