package me.ivanlis.shrinker.services;

import lombok.RequiredArgsConstructor;
import me.ivanlis.shrinker.algos.Shortener;
import me.ivanlis.shrinker.data.Link;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlShortener {

    private final Shortener shortener;

    public Link encode(String link) {
        String s = shortener.encode(link);
        return new Link(s, link);
    }

}
