package com.example.demo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<Meme, String> {
	Optional<Meme> findByUrl(String url);
	Optional<Meme> findByName(String name);
	Optional<Meme> findByCaption(String caption);
}
