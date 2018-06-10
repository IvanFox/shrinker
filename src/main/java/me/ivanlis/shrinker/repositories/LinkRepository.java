package me.ivanlis.shrinker.repositories;

import me.ivanlis.shrinker.data.Link;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LinkRepository extends ReactiveMongoRepository<Link, String> {

    Mono<Link> findByShortUrl(String shortLink);
}
