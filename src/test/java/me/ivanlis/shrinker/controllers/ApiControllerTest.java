package me.ivanlis.shrinker.controllers;

import me.ivanlis.shrinker.api.ApiLinkModel;
import me.ivanlis.shrinker.services.Orchestrator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ApiControllerTest {

    WebTestClient webTestClient;


    @Mock
    Orchestrator orchestrator;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        webTestClient = WebTestClient.bindToController(new ApiController(orchestrator)).configureClient().build();
    }

    @Test
    public void findLink() {
        String endpoint = "find/?shortLink=";
        String shortenUrl = "u8s1";

        Mockito.when(orchestrator.findByShortUrl(shortenUrl)).thenReturn(Mono.empty());

        webTestClient.get().uri(endpoint + shortenUrl)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ApiLinkModel.class);
    }

    @Test
    public void findAll() {
        String endpoint = "find/all";

        Mockito.when(orchestrator.findAll()).thenReturn(Flux.empty());

        webTestClient.get().uri(endpoint)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(ApiLinkModel.class);
    }

    @Test
    public void createLink() {
        String url = "ivanfox.com";
        Mockito.when(orchestrator.createLink(url)).thenReturn(ApiLinkModel.of("sh", url));


        webTestClient.post().uri("/create")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(url), String.class)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody();
    }
}