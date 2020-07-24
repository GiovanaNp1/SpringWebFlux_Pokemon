package com.webflux.pokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import com.webflux.pokedex.model.Pokemon;
import com.webflux.pokedex.repository.PokemonRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
		}
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/pokemon").allowedOrigins("http://localhost:8080");
				}
			};
		}

		
		@Bean 
		CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository) {
			return args -> {
				Flux<Pokemon> pokemonFlux = Flux.just(
					new Pokemon(null, "shellder", "water", "shell-armor", 40.5, "https://cdn.bulbagarden.net/upload/c/cb/Spr_3f_090.png"),
					new Pokemon(null, "lickitung", "normal", "special-attack", 65.5, "https://cdn.bulbagarden.net/upload/d/d3/Spr_4h_108.png"),
					new Pokemon(null, "wartortle", "water", "ice-punch", 225, "https://www.pngkey.com/png/full/154-1541978_wartortle-wartortle-pixel-gif.png"),
					new Pokemon(null, "bulbasaur", "grass", "grass-pledge", 6.9, "http://pixelartmaker.com/art/b685b6640e4ee75.png"))
					.flatMap(repository::save);
				
				pokemonFlux
						.thenMany(repository.findAll())
						.subscribe(System.out::println);
			};
		}
}
