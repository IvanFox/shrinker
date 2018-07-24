package me.ivanlis.shrinker.api;

import lombok.Value;

@Value(staticConstructor = "of")
public class ApiLinkModel {

    String shortUrl;

    String url;

}
