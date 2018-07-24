package me.ivanlis.shrinker.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.api.ApiLinkModel;
import me.ivanlis.shrinker.data.Link;
import me.ivanlis.shrinker.services.Orchestrator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApiController {

    private final Orchestrator orchestrator;

    @GetMapping("/find")
    public Mono<ApiLinkModel> findLink(@RequestParam String shortLink) {
        log.info("Retrieving full url using: {}", shortLink);
        return orchestrator.findByShortUrl(shortLink).map(Link::getAsApiModel);
    }

    @GetMapping("/find/all")
    public Flux<ApiLinkModel> findAll() {
        return orchestrator.findAll().map(Link::getAsApiModel);
    }

    @PostMapping(path = "/create", consumes = "application/json")
    public Mono<ApiLinkModel> createLink(@RequestBody String url) {
        log.info("Received url: {} for shortening. ", url);
        return Mono.fromSupplier(() -> orchestrator.createLink(url));
    }



}
