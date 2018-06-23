package me.ivanlis.shrinker.services;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.api.ApiLink;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkPublisher {

    public static final String TOPIC = "publish_link";

    private static final Gson GSON = new Gson();

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(ApiLink apiLink) {
        log.info("Sending link to kafka. {}", apiLink);
        kafkaTemplate.send(TOPIC, apiLink.getLink(), GSON.toJson(apiLink));
    }

}
