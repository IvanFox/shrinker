package me.ivanlis.shrinker.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.data.Link;
import me.ivanlis.shrinker.repositories.LinkRepository;
import me.ivanlis.shrinker.services.UrlShortener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApiController {

    private final LinkRepository linkRepository;

    private final UrlShortener urlShortener;

    @GetMapping("/find")
    public Mono<Link> findByTitle(@RequestParam String shortLink) {
        log.info("Retrieving full link using: {}", shortLink);
        return linkRepository.findByShortLink(shortLink);
    }

    @PostMapping(path = "/create", consumes = "application/json")
    public Mono<Link> createLink(@RequestBody String url) {
        log.info("Received url: {} for shortening. ", url);
        return Mono.fromSupplier(() -> urlShortener.encode(url));
    }



}
