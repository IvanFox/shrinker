package me.ivanlis.shrinker.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.ivanlis.shrinker.algos.Shortener;
import me.ivanlis.shrinker.api.ApiLink;
import me.ivanlis.shrinker.data.Link;
import me.ivanlis.shrinker.repositories.LinkRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class Orchestrator {

    private final LinkRepository linkRepository;

    private final Shortener shortener;

    private final LinkPublisher publisher;

    public ApiLink createLink(String url) {
        String shortUrl = shortener.encode(url);
        val link = ApiLink.of(shortUrl, url);
        publisher.send(link);
        return link;

    }

    public Mono<Link> findByShortUrl(String shortUrl) {
        return linkRepository.findByShortUrl(shortUrl);
    }

    public Flux<Link> findAll() {
        return linkRepository.findAll();
    }

}
