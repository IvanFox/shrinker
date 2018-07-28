package me.ivanlis.shrinker.services;

import static me.ivanlis.shrinker.utils.JsonSerialiser.JSON_SERIALISER;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.api.ApiLinkModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkPublisher {

    public static final String TOPIC = "publish_link";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(ApiLinkModel apiLinkModel) {
        log.info("Sending link to kafka. {}", apiLinkModel);
        kafkaTemplate.send(TOPIC, apiLinkModel.getUrl(), JSON_SERIALISER.toJson(apiLinkModel));
    }

}
