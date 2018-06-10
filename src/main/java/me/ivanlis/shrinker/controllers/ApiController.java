package me.ivanlis.shrinker.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.api.ApiLink;
import me.ivanlis.shrinker.data.Link;
import me.ivanlis.shrinker.services.Orchestrator;
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

    private final Orchestrator orchestrator;

    @GetMapping("/find")
    public Mono<ApiLink> findLink(@RequestParam String shortLink) {
        log.info("Retrieving full url using: {}", shortLink);
        return orchestrator.findByShortUrl(shortLink).map(Link::getApiLink);
    }

    @PostMapping(path = "/create", consumes = "application/json")
    public Mono<ApiLink> createLink(@RequestBody String url) {
        log.info("Received url: {} for shortening. ", url);
        return Mono.fromSupplier(() -> orchestrator.createLink(url));
    }



}
