package me.ivanlis.shrinker.services;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.api.ApiLinkModel;
import me.ivanlis.shrinker.data.Link;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiModelHandler {

    private final Orchestrator orchestrator;

    public Mono<ServerResponse> findLink(String shortLink) {
        log.info("Retrieving full url using: {}", shortLink);

        Mono<ApiLinkModel> apiLink = orchestrator.findByShortUrl(shortLink).map(Link::getAsApiModel);

        return apiLink
            .flatMap(link -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(link)))
            .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> findAll() {
        Flux<ApiLinkModel> linkModels = orchestrator.findAll().map(Link::getAsApiModel);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(linkModels, ApiLinkModel.class);

    }

    public Mono<ServerResponse> createLink(String url) {
        log.info("Received url: {} for shortening. ", url);
        Mono<ApiLinkModel> apiLink = Mono.fromSupplier(() -> orchestrator.createLink(url));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(apiLink, ApiLinkModel.class);
    }



}
