package me.ivanlis.shrinker.services;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import me.ivanlis.shrinker.api.ApiLinkModel;
import me.ivanlis.shrinker.data.Link;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ApiModelHandlerTest {

    @Mock
    private Orchestrator orchestrator;


    private ApiModelHandler apiModelHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        apiModelHandler = new ApiModelHandler(orchestrator);
    }

    @Test
    public void findLink() {
        String url = "e45a";
        when(orchestrator.findByShortUrl(url)).thenReturn(Mono.just(new Link(url, "test.com")));

        Mono<ServerResponse> response = apiModelHandler.findLink(url);


        response.subscribe(r -> Assert.assertEquals(HttpStatus.OK, r.statusCode()));

    }

    @Test
    public void findLinkNotFound() {
        String url = "e45a";
        when(orchestrator.findByShortUrl(url)).thenReturn(Mono.empty());

        Mono<ServerResponse> response = apiModelHandler.findLink(url);


        response.subscribe(r -> Assert.assertEquals(HttpStatus.NOT_FOUND, r.statusCode()));

    }

    @Test
    public void findAllFound() {
        when(orchestrator.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(new Link("rh32", "test.com"))));

        Mono<ServerResponse> response = apiModelHandler.findAll();

        response.subscribe(r -> Assert.assertEquals(HttpStatus.OK, r.statusCode()));
    }

    @Test
    public void createLink() {
        String url = "test.com";
        when(orchestrator.createLink(url)).thenReturn(ApiLinkModel.of("rh32", url));

        Mono<ServerResponse> response = apiModelHandler.createLink(url);

        response.subscribe(r -> Assert.assertEquals(HttpStatus.OK, r.statusCode()));
    }
}