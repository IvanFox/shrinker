package me.ivanlis.shrinker.configurations;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import me.ivanlis.shrinker.services.ApiModelHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFlux
class WebConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ApiModelHandler apiModelHandler) {
        return
            route(GET("/find/all"), request -> apiModelHandler.findAll()).
                andRoute(GET("/find/{shortLink}"), request -> {
                    String shortLink = request.pathVariable("shortLink");
                    return apiModelHandler.findLink(shortLink);
                })
                .andRoute(POST("/create").and(contentType(APPLICATION_JSON)), request -> {
                    Mono<String> url = request.body(BodyExtractors.toMono(String.class));
                    return url.flatMap(apiModelHandler::createLink);
                });
    }
}
