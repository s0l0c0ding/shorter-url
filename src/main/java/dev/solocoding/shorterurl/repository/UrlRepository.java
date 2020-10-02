package dev.solocoding.shorterurl.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import dev.solocoding.shorterurl.entity.Url;

public interface UrlRepository extends MongoRepository<Url, ObjectId> {

	Optional<Url> findByShortUrl(String shortUrl);
    
}
