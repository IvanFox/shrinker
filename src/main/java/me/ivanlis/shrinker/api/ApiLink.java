package me.ivanlis.shrinker.api;

import lombok.Value;

@Value(staticConstructor = "of")
public class ApiLink {

    String shortUrl;

    String url;

}
